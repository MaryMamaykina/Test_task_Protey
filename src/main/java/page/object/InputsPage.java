package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InputsPage {
    private final WebDriver webDriver;
    public InputsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    private final By addButton = By.id("dataSend");
    public void waitForPageToBeVisible(){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.elementToBeClickable(addButton));
    }
}
