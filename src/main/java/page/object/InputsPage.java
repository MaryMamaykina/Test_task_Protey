package page.object;

import data.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    private final By successMessage = By.xpath("//div[text()='Данные добавлены.']");
    private final By emailFormatErrorMessage = By.id("emailFormatError");
    private final By nameCannotBeEmptyError = By.id("blankNameError");

    public void waitForPageToLoad(){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.elementToBeClickable(addButton));
    }
    public void waitForElementToBeVisible(By locator){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void fillInRequiredFields(String email, String name){
        fillingInEmailField(email);
        fillingInNameField(name);
        clickField(addButton);
    }
    public void fillInAllForm(String email, String name, Gender gender, List<Option1> option1ChosenVariants, Option2 option2){
        fillingInEmailField(email);
        fillingInNameField(name);
        choosingGender(gender);
        choosingOption1(option1ChosenVariants);
        choosingOption2(option2);
        clickField(addButton);
    }
    public String getTextFromTableFromFirstRow(TableColumns tableColumns){
        By locator = By.xpath(getLocatorForTableElementFromFirstRow(tableColumns));
        return webDriver.findElement(locator).getText();
    }
    public void checkingResult(FillingInTableResults resultOfFillingInFields){
        switch (resultOfFillingInFields){
            case SUCCESS:
                waitForElementToBeVisible(successMessage);
                break;
            case WRONG_EMAIL_FORMAT_ERROR:
                waitForElementToBeVisible(emailFormatErrorMessage);
                break;
            case NAME_CANNOT_BE_EMPTY_ERROR:
                waitForElementToBeVisible(nameCannotBeEmptyError);
                break;
        }
    }


    private String getLocatorForTableElement(TableColumns tableColumns, int numRow){
        String locatorWay = null;
        switch (tableColumns) {
            case EMAIL:
                locatorWay = "//table[@id='dataTable']/tbody/tr[" + numRow + "]/td[1]";
                break;
            case NAME:
                locatorWay = "//table[@id='dataTable']/tbody/tr[" + numRow + "]/td[2]";
                break;
            case GENDER:
                locatorWay = "//table[@id='dataTable']/tbody/tr[" + numRow + "]/td[3]";
                break;
            case OPTION1:
                locatorWay = "//table[@id='dataTable']/tbody/tr[" + numRow + "]/td[4]";
                break;
            case OPTION2:
                locatorWay = "//table[@id='dataTable']/tbody/tr[" + numRow + "]/td[5]";
                break;
        }
        System.out.println(locatorWay);
        return locatorWay;
    }
    private String getLocatorForTableElementFromFirstRow(TableColumns tableColumns){
        int numRow = 1;
        String locatorWay = null;
        switch (tableColumns) {
            case EMAIL:
                locatorWay = "//table[@id='dataTable']/tbody/tr[" + numRow + "]/td[1]";
                break;
            case NAME:
                locatorWay = "//table[@id='dataTable']/tbody/tr[" + numRow + "]/td[2]";
                break;
            case GENDER:
                locatorWay = "//table[@id='dataTable']/tbody/tr[" + numRow + "]/td[3]";
                break;
            case OPTION1:
                locatorWay = "//table[@id='dataTable']/tbody/tr[" + numRow + "]/td[4]";
                break;
            case OPTION2:
                locatorWay = "//table[@id='dataTable']/tbody/tr[" + numRow + "]/td[5]";
                break;
        }
        return locatorWay;
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
    private void choosingOption1(List<Option1> option1){
        for (Option1 optionToClick : option1) {
            switch (optionToClick) {
                case NONE:
                    break;
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
