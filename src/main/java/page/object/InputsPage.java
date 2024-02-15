package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import result.Gender;
import result.Option1;
import result.Option2;

import java.time.Duration;
import java.util.List;

public class InputsPage {
    private final WebDriver webDriver;
    public InputsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    private final By addButton = By.id("dataSend");
    private final By emailField = By.id("dataEmail");
    private final By nameField = By.id("dataName");
    private final By genderSelection = By.id("dataGender");
    private final By female = By.xpath("//option[text()='Женский']");
    private final By option11 = By.id("dataCheck11");
    private final By option12 = By.id("dataCheck12");
    private final By option21 = By.id("dataSelect21");
    private final By option22 = By.id("dataSelect22");
    private final By option23 = By.id("dataSelect23");

    public void waitForPageToBeClickable(){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.elementToBeClickable(addButton));
    }
    public void fillInRequiredFields(String email, String name){
        fillingInEmailField(email);
        fillingInNameField(name);
        clickField(addButton);
    }
    public void fillInAllForm(String email, String name, Gender gender, Option1 option1, Option2 option2){
        fillingInEmailField(email);
        fillingInNameField(name);
        choosingGender(gender);
        choosingOption1(option1);
        choosingOption2(option2);
        clickField(addButton);
    }
    private void fillingInEmailField(String email){
        clickField(emailField);
        addTextIntoField(emailField, email);
    }
    private void fillingInNameField(String name){
        clickField(nameField);
        addTextIntoField(nameField, name);
    }
    private void choosingGender(Gender gender){
        switch (gender) {
            case MALE:
                break;
            case FEMALE:
                clickField(genderSelection);
                clickField(female);
                break;
        }
    }

    private void choosingOption1(Option1 option1){
        switch (option1) {
            case NEITHER_ONE:
                break;
            case ONE:
                clickField(option11);
                break;
            case TWO:
                clickField(option12);
                break;
            case BOTH:
                clickField(option11);
                clickField(option12);
                break;
        }
    }
    private void choosingOption1(List<Option1> option1){
        for (Option1 optionToClick : option1) {
            switch (optionToClick) {
                case ONE:
                    clickField(option11);
                    break;
                case TWO:
                    clickField(option12);
                    break;
            }
        }

    }

    private void choosingOption2(Option2 option2){
        switch (option2) {
            case ONE:
                clickField(option21);
                break;
            case TWO:
                clickField(option22);
                break;
            case THREE:
                clickField(option23);
                break;
        }
    }




    private void clickField(By locator){
        webDriver.findElement(locator).click();
    }
    private void addTextIntoField(By locatorField, String text){
        webDriver.findElement(locatorField).sendKeys(text);
    }
}
