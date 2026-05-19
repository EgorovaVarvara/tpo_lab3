import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.AccountPage;
import org.example.pages.AuthPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountTest {
    private WebDriver driver;
    private AuthPage authPage;
    private AccountPage accountPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        authPage = new AuthPage(driver);
        authPage.open();
        authPage.login(
                "tashkinova.2017@inbox.ru",
                "qwerty"
        );
        accountPage = new AccountPage(driver);
        accountPage.open();
    }

    @Test
    public void logoutTest(){
        accountPage.logout();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver ->
                driver.getCurrentUrl().contains("signin"));

        Assertions.assertTrue(driver.getCurrentUrl().contains("signin"));
    }

    @Test
    public void plusTest(){
        accountPage.clickPlus();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver ->
                driver.getCurrentUrl().contains("plus"));

        Assertions.assertTrue(driver.getCurrentUrl().contains("plus"));
    }

    @Test
    public void changeNameTest(){
        accountPage.changeName("qwerty1");
        accountPage.submitChangeName();

        Assertions.assertTrue(accountPage.isChangeNameMessageDisplayed());

        accountPage.open();
        accountPage.changeName("qwerty1");

        Assertions.assertFalse(accountPage.ifSubmitNameButtonEnabled());
    }

    @Test
    public void changeEmptyNameTest(){
        accountPage.changeName("");

        Assertions.assertFalse(accountPage.ifSubmitNameButtonEnabled());
    }

    @Test
    public void changeEmailTest(){
        accountPage.changeEmail();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver ->
                driver.getCurrentUrl().contains("contact-us"));

        Assertions.assertTrue(driver.getCurrentUrl().contains("contact-us"));
    }

    @Test
    public void changeEmptyPasswordTest(){
        accountPage.changePassword("", "");

        Assertions.assertTrue(accountPage.isWrongFormInputMessageDisplayed());
        Assertions.assertTrue(accountPage.isWrongPasswordErrorMessageDisplayed());
    }

    @Test
    public void changeUnmatchedPasswordTest(){
        accountPage.changePassword("qwerty", "qwert");

        Assertions.assertTrue(accountPage.isWrongFormInputMessageDisplayed());
        Assertions.assertTrue(accountPage.isUnmatchedPasswordsErrorMessage());
    }

    @Test
    public void changePasswordTest(){
        accountPage.changePassword("qwerty", "qwerty");

        Assertions.assertTrue(accountPage.isChangePasswordMessageDisplayed());
    }

    @Test
    public void openPrivacyTest(){
        accountPage.openPrivacy();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver ->
                driver.getCurrentUrl().contains("privacy"));

        Assertions.assertTrue(driver.getCurrentUrl().contains("privacy"));
    }

    @Test
    public void openTermsTest(){
        accountPage.openTerms();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver ->
                driver.getCurrentUrl().contains("terms"));

        Assertions.assertTrue(driver.getCurrentUrl().contains("terms"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
