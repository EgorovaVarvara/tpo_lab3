import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.EnterPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AuthTest {
    private WebDriver driver;
    private EnterPage signInPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        signInPage = new EnterPage(driver);
        signInPage.open();
    }

    @Test
    public void invalidLoginTest() {

        signInPage.login(
                "wrong@email.com",
                "wrongpassword"
        );

        Assertions.assertTrue(
                signInPage.isErrorMessageDisplayed()
        );
    }

    @Test
    public void openLoginPageTest() {

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("signin")
        );
    }

    @Test
    public void loginFormElementsTest() {

        Assertions.assertTrue(
                driver.getPageSource().contains("email")
        );

        Assertions.assertTrue(
                driver.getPageSource().contains("password")
        );
    }

    @Test
    public void goToSignUpTest() {

        signInPage.goToSignUp();

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("signup")
        );
    }

    @Test
    public void goToForgotPasswordPageTest(){

        signInPage.goToForgotPassPage();

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("password")
        );

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("forgot")
        );
    }

    @Test
    public void goToPrivacyPageTest(){

        signInPage.goToPrivacy();

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("privacy")
        );
    }

    @Test
    public void goToTermsPageTest(){

        signInPage.goToTerms();

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("terms")
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
