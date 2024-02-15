import data.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.object.AuthPage;
import page.object.InputsPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputsPageTest {
    private WebDriver webDriver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
        webDriver = new ChromeDriver();
        AuthPage objAuthPage = new AuthPage(webDriver);
        objAuthPage.successfulAuthorization();
    }

    @Test
    public void fillOutFormUsingRequiredDataGetSuccessMessage() {
        InputsPage objInputsPage = new InputsPage(webDriver);
        String email = "ivanov@protei.ru";
        String name = "Ivan Ivanov";
        objInputsPage.fillInRequiredFields(email, name);
        objInputsPage.checkingResult(FillingInTableResults.SUCCESS);

    }

    @Test
    public void fillOutAllFormGetSuccessMessage() {
        InputsPage objInputsPage = new InputsPage(webDriver);
        String email = "ivanov@protei.ru";
        String name = "Ivan Ivanov";
        Gender gender = Gender.FEMALE;
        List<Option1> option1 = new ArrayList<>();
        option1.add(0, Option1.ONE);
        Option2 option2 = Option2.ONE;
        objInputsPage.fillInAllForm(email, name, gender, option1, option2);
        objInputsPage.checkingResult(FillingInTableResults.SUCCESS);
    }

    @Test
    public void fieldEmailGetRightResultsInTable() {
        InputsPage objInputsPage = new InputsPage(webDriver);
        String email = "ivanov@protei.ru";
        String name = "Ivan Ivanov";
        objInputsPage.fillInRequiredFields(email, name);
        String actualEmail = objInputsPage.getTextFromTableFromFirstRow(TableColumns.EMAIL);
        Assert.assertEquals("Email", email, actualEmail);
    }

    @Test
    public void fieldNameGetRightResultsInTable() {
        InputsPage objInputsPage = new InputsPage(webDriver);
        String email = "ivanov@protei.ru";
        String name = "Ivan Ivanov";
        objInputsPage.fillInRequiredFields(email, name);
        String actualName = objInputsPage.getTextFromTableFromFirstRow(TableColumns.NAME);
        Assert.assertEquals("Email", name, actualName);
    }

    @Test
    public void informationAboutGenderFemaleGetRightResultsInTable() {
        InputsPage objInputsPage = new InputsPage(webDriver);
        String email = "ivanov@protei.ru";
        String name = "Ivan Ivanov";
        Gender gender = Gender.FEMALE;
        List<Option1> option1 = new ArrayList<>();
        option1.add(0, Option1.ONE);
        Option2 option2 = Option2.ONE;

        objInputsPage.fillInAllForm(email, name, gender, option1, option2);
        String actualGender = objInputsPage.getTextFromTableFromFirstRow(TableColumns.GENDER);

        Assert.assertEquals("Gender", gender.getText(), actualGender);

    }

    @Test
    public void informationAboutGenderMaleGetRightResultsInTable() {
        InputsPage objInputsPage = new InputsPage(webDriver);
        String email = "ivanov@protei.ru";
        String name = "Ivan Ivanov";
        Gender gender = Gender.MALE;
        List<Option1> option1 = new ArrayList<>();
        option1.add(0, Option1.ONE);
        Option2 option2 = Option2.ONE;

        objInputsPage.fillInAllForm(email, name, gender, option1, option2);
        String actualGender = objInputsPage.getTextFromTableFromFirstRow(TableColumns.GENDER);

        Assert.assertEquals("Gender", gender.getText(), actualGender);

    }

    @Test
    public void informationAboutOption1NoneGetRightResultsInTable() {
        InputsPage objInputsPage = new InputsPage(webDriver);
        String email = "ivanov@protei.ru";
        String name = "Ivan Ivanov";
        Gender gender = Gender.FEMALE;
        List<Option1> option1 = new ArrayList<>();
        Option2 option2 = Option2.ONE;

        objInputsPage.fillInAllForm(email, name, gender, option1, option2);
        String actualOption1 = objInputsPage.getTextFromTableFromFirstRow(TableColumns.OPTION1);
        Assert.assertEquals("Option1", Option1.NONE.getText(), actualOption1);

    }

    @Test
    public void informationAboutOption1OneGetRightResultsInTable() {
        InputsPage objInputsPage = new InputsPage(webDriver);
        String email = "ivanov@protei.ru";
        String name = "Ivan Ivanov";
        Gender gender = Gender.FEMALE;
        List<Option1> option1 = new ArrayList<>();
        option1.add(0, Option1.ONE);
        Option2 option2 = Option2.ONE;

        objInputsPage.fillInAllForm(email, name, gender, option1, option2);
        String actualOption1 = objInputsPage.getTextFromTableFromFirstRow(TableColumns.OPTION1);
        Assert.assertEquals("Option1", Option1.ONE.getText(), actualOption1);

    }

    @Test
    public void informationAboutOption1TwoGetRightResultsInTable() {
        InputsPage objInputsPage = new InputsPage(webDriver);
        String email = "ivanov@protei.ru";
        String name = "Ivan Ivanov";
        Gender gender = Gender.FEMALE;
        List<Option1> option1 = new ArrayList<>();
        option1.add(0, Option1.TWO);
        Option2 option2 = Option2.ONE;

        objInputsPage.fillInAllForm(email, name, gender, option1, option2);
        String actualOption1 = objInputsPage.getTextFromTableFromFirstRow(TableColumns.OPTION1);
        Assert.assertEquals("Option1", Option1.TWO.getText(), actualOption1);

    }

    @Test
    public void informationAboutOptionBothGetRightResultsInTable() {
        InputsPage objInputsPage = new InputsPage(webDriver);
        String email = "ivanov@protei.ru";
        String name = "Ivan Ivanov";
        Gender gender = Gender.FEMALE;
        List<Option1> option1 = new ArrayList<>();
        option1.add(0, Option1.ONE);
        option1.add(1, Option1.TWO);
        Option2 option2 = Option2.ONE;

        objInputsPage.fillInAllForm(email, name, gender, option1, option2);
        String actualOption1 = objInputsPage.getTextFromTableFromFirstRow(TableColumns.OPTION1);
        String[] chosenOption1 = actualOption1.split(", ");

        Assert.assertEquals("Option1", option1.size(), chosenOption1.length);
        Assert.assertTrue("Option1", Arrays.stream(chosenOption1).allMatch((option) -> {
            return option1.stream().anyMatch(expectedOption -> {
                return expectedOption.getText().equals(option);
            });
        }));
    }

    @Test
    public void informationAboutOption2OneGetRightResultsInTable() {
        InputsPage objInputsPage = new InputsPage(webDriver);
        String email = "ivanov@protei.ru";
        String name = "Ivan Ivanov";
        Gender gender = Gender.MALE;
        List<Option1> option1 = new ArrayList<>();
        option1.add(0, Option1.TWO);
        Option2 option2 = Option2.ONE;

        objInputsPage.fillInAllForm(email, name, gender, option1, option2);
        String actualOption2 = objInputsPage.getTextFromTableFromFirstRow(TableColumns.OPTION2);
        Assert.assertEquals("Option2", option2.getText(), actualOption2);
    }

    @Test
    public void informationAboutOption2TwoGetRightResultsInTable() {
        InputsPage objInputsPage = new InputsPage(webDriver);
        String email = "ivanov@protei.ru";
        String name = "Ivan Ivanov";
        Gender gender = Gender.MALE;
        List<Option1> option1 = new ArrayList<>();
        option1.add(0, Option1.TWO);
        Option2 option2 = Option2.TWO;

        objInputsPage.fillInAllForm(email, name, gender, option1, option2);
        String actualOption2 = objInputsPage.getTextFromTableFromFirstRow(TableColumns.OPTION2);
        Assert.assertEquals("Option2", option2.getText(), actualOption2);
    }

    @Test
    public void informationAboutOption2ThreeGetRightResultsInTable() {
        InputsPage objInputsPage = new InputsPage(webDriver);
        String email = "ivanov@protei.ru";
        String name = "Ivan Ivanov";
        Gender gender = Gender.MALE;
        List<Option1> option1 = new ArrayList<>();
        option1.add(0, Option1.TWO);
        Option2 option2 = Option2.THREE;

        objInputsPage.fillInAllForm(email, name, gender, option1, option2);
        String actualOption2 = objInputsPage.getTextFromTableFromFirstRow(TableColumns.OPTION2);
        Assert.assertEquals("Option2", option2.getText(), actualOption2);

    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
