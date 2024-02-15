import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.object.AuthPage;
import result.AuthorizationData;
import result.ResultOfFillingInFields;

@RunWith(Parameterized.class)
public class AuthPageParamTest {
    private WebDriver webDriver;
    public static String variableForCreatingLargeText = "a";

    @Parameterized.Parameter(0)
    public String emailForAuth;
    @Parameterized.Parameter(1)
    public String passwordForAuth;
    @Parameterized.Parameter(2)
    public ResultOfFillingInFields result;

    @Before
    public void setup() {

        WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
        webDriver = new ChromeDriver();
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {AuthorizationData.CORRECT_EMAIL_FOR_AUTH, AuthorizationData.CORRECT_PASSWORD_FOR_AUTH, ResultOfFillingInFields.SUCCESS},
                {"", "", ResultOfFillingInFields.WRONG_EMAIL_FORMAT_ERROR},
                {AuthorizationData.CORRECT_EMAIL_FOR_AUTH, "", ResultOfFillingInFields.WRONG_EMAIL_OR_PASSWORD_ERROR},
                {"", AuthorizationData.CORRECT_PASSWORD_FOR_AUTH, ResultOfFillingInFields.WRONG_EMAIL_FORMAT_ERROR},
                {"test@protei", AuthorizationData.CORRECT_PASSWORD_FOR_AUTH, ResultOfFillingInFields.WRONG_EMAIL_OR_PASSWORD_ERROR},
                {AuthorizationData.CORRECT_EMAIL_FOR_AUTH, "tset", ResultOfFillingInFields.WRONG_EMAIL_OR_PASSWORD_ERROR},
                {"testprotei.ru", AuthorizationData.CORRECT_PASSWORD_FOR_AUTH, ResultOfFillingInFields.WRONG_EMAIL_FORMAT_ERROR},
                {AuthorizationData.CORRECT_EMAIL_FOR_AUTH, variableForCreatingLargeText.repeat(10000), ResultOfFillingInFields.WRONG_EMAIL_OR_PASSWORD_ERROR}
        };
    }

    @Test
    public void checkDependenceResultFromEmailAndPassword(){
        AuthPage authPage = new AuthPage(webDriver);
        authPage.openPage();
        authPage.authorization(emailForAuth, passwordForAuth);
        authPage.checkingResult(result);
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
