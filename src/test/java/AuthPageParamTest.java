import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.object.AuthPage;
import data.AuthorizationConsts;
import data.AuthorisationResults;

@RunWith(Parameterized.class)
public class AuthPageParamTest {
    private WebDriver webDriver;
    public static String variableForCreatingLargeText = "a";

    @Parameterized.Parameter(0)
    public String emailForAuth;
    @Parameterized.Parameter(1)
    public String passwordForAuth;
    @Parameterized.Parameter(2)
    public AuthorisationResults result;

    @Before
    public void setup() {

        WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
        webDriver = new ChromeDriver();
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {AuthorizationConsts.CORRECT_EMAIL_FOR_AUTH, AuthorizationConsts.CORRECT_PASSWORD_FOR_AUTH, AuthorisationResults.SUCCESS},
                {"", "", AuthorisationResults.WRONG_EMAIL_FORMAT_ERROR},
                {AuthorizationConsts.CORRECT_EMAIL_FOR_AUTH, "", AuthorisationResults.WRONG_EMAIL_OR_PASSWORD_ERROR},
                {"", AuthorizationConsts.CORRECT_PASSWORD_FOR_AUTH, AuthorisationResults.WRONG_EMAIL_FORMAT_ERROR},
                {"test@protei", AuthorizationConsts.CORRECT_PASSWORD_FOR_AUTH, AuthorisationResults.WRONG_EMAIL_OR_PASSWORD_ERROR},
                {AuthorizationConsts.CORRECT_EMAIL_FOR_AUTH, "tset", AuthorisationResults.WRONG_EMAIL_OR_PASSWORD_ERROR},
                {"testprotei.ru", AuthorizationConsts.CORRECT_PASSWORD_FOR_AUTH, AuthorisationResults.WRONG_EMAIL_FORMAT_ERROR},
                {AuthorizationConsts.CORRECT_EMAIL_FOR_AUTH, variableForCreatingLargeText.repeat(10000), AuthorisationResults.WRONG_EMAIL_OR_PASSWORD_ERROR}
        };
    }

    @Test
    public void checkDependenceResultFromEmailAndPassword(){
        AuthPage objAuthPage = new AuthPage(webDriver);
        objAuthPage.openPage();
        objAuthPage.authorization(emailForAuth, passwordForAuth);
        objAuthPage.checkingResult(result);
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
