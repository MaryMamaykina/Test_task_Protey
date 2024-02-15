import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.object.AuthPage;
import result.ResultOfFillingInFields;

@RunWith(Parameterized.class)
public class AuthPageParamTest {
    private WebDriver webDriver;
    @Parameterized.Parameter(0)
    public String email;
    @Parameterized.Parameter(1)
    public String password;
    @Parameterized.Parameter(2)
    public ResultOfFillingInFields result;

    @Before
    public void setup() {

        WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
        webDriver = new ChromeDriver();
    }
    public static String variableForCreatingLargeText = "a";
    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"test@protei.ru", "test", ResultOfFillingInFields.SUCCESS},
                {"", "", ResultOfFillingInFields.WRONG_EMAIL_FORMAT_ERROR},
                {"test@protei.ru", "", ResultOfFillingInFields.WRONG_EMAIL_OR_PASSWORD_ERROR},
                {"", "test", ResultOfFillingInFields.WRONG_EMAIL_FORMAT_ERROR},
                {"test@protei", "test", ResultOfFillingInFields.WRONG_EMAIL_OR_PASSWORD_ERROR},
                {"test@protei.ru", "tset", ResultOfFillingInFields.WRONG_EMAIL_OR_PASSWORD_ERROR},
                {"test", "test", ResultOfFillingInFields.WRONG_EMAIL_FORMAT_ERROR},
                {"test@protei.ru", variableForCreatingLargeText.repeat(10000), ResultOfFillingInFields.WRONG_EMAIL_OR_PASSWORD_ERROR}

        };
    }

    @Test
    public void checkDependenceResultFromEmailAndPassword(){
        AuthPage authPage = new AuthPage(webDriver);
        authPage.openPage();
        authPage.authorization(email, password);
        authPage.checkingResult(result);

    }

    @After
    public void teardown() {
        webDriver.quit();
    }


}
