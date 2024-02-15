import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.object.AuthPage;
import result.ResultOfFillingInFields;

@RunWith(Parameterized.class)
public class InputsPageParamTest {
    private WebDriver webDriver;
    @Parameterized.Parameter(0)
    public String emailToAdd;
    @Parameterized.Parameter(1)
    public String NameToAdd;

    @Before
    public void setup() {

        WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
        webDriver = new ChromeDriver();
        AuthPage authPage = new AuthPage(webDriver);
        authPage.successfulAuthorization();
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"ivanov-i@protei.ru", "Ivan Ivanov"},

        };
    }

    @Test
    public void (){


    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
