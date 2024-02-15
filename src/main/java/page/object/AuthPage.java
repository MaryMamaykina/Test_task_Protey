package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import result.ResultOfFillingInFields;

import java.time.Duration;

public class AuthPage {
    private final WebDriver webDriver;
    public AuthPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    //Кнопка Вход
    private final By authButton = By.id("authButton");
    private final By inputFieldEmail = By.id("loginEmail");
    private final By inputFieldPassword = By.id("loginPassword");
    private final By emailFormatErrorMessage = By.id("emailFormatError");
    private final By wrongEmailOrPasswordErrorMessage = By.id("invalidEmailPassword");


    public void openPage() {
        webDriver.get("file:///"+System.getProperty("user.dir")+"/qa-test.html");
    }

    public void authorization(String email, String password){
        waitForElementToBeClickable(authButton);
        clickField(inputFieldEmail);
        addTextIntoField(inputFieldEmail, email);
        clickField(inputFieldPassword);
        addTextIntoField(inputFieldPassword, password);
        clickField(authButton);
    }
    public void checkingResult(ResultOfFillingInFields resultOfFillingInFields){
        switch (resultOfFillingInFields){
            case SUCCESS:
                InputsPage inputsPage =new InputsPage(webDriver);
                inputsPage.waitForPageToBeVisible();
                break;
            case WRONG_EMAIL_FORMAT_ERROR:
                waitForElementToBeVisible(emailFormatErrorMessage);
                break;
            case WRONG_EMAIL_OR_PASSWORD_ERROR:
                waitForElementToBeVisible(wrongEmailOrPasswordErrorMessage);
                break;
        }
    }
    private void waitForElementToBeVisible(By locator){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    private void waitForElementToBeClickable(By locator){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
    private void clickField(By locator){
        webDriver.findElement(locator).click();
    }
    private void addTextIntoField(By locatorField, String text){
        webDriver.findElement(locatorField).sendKeys(text);
    }


}
