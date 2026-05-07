import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.AuthPage;
import org.example.pages.RegPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegTest {
    private WebDriver driver;
    private RegPage signUpPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        signUpPage = new RegPage(driver);
        signUpPage.open();
    }

    @Test
    public void goToSignInTest(){
        signUpPage.goToSignIn();

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("signin")
        );
    }

    @Test
    public void emptyFormTest(){
        signUpPage.login(
                "",
                "",
                "",
                false
        );

        Assertions.assertTrue(
                signUpPage.isWrongFormInputErrorMessageDisplayed()
        );

        Assertions.assertTrue(
                signUpPage.isEmptyInputErrorMessageDisplayed()
        );

        Assertions.assertTrue(
                signUpPage.isEmptyCheckboxErrorMessageDisplayed()
        );
    }

    @Test
    public void emptyNameTest(){
        signUpPage.login(
                "",
                "evorovarvar@gmail.com",
                "12345",
                true
        );
        Assertions.assertTrue(
                signUpPage.isWrongFormInputErrorMessageDisplayed()
        );
        Assertions.assertTrue(
                signUpPage.isEmptyInputErrorMessageDisplayed()
        );
    }

    @Test
    public void emptyEmailTest(){
        signUpPage.login(
                "12345",
                "",
                "12345",
                true
        );
        Assertions.assertTrue(
                signUpPage.isWrongFormInputErrorMessageDisplayed()
        );
        Assertions.assertTrue(
                signUpPage.isEmptyInputErrorMessageDisplayed()
        );
    }

    @Test
    public void wrongEmailTest(){
        signUpPage.login(
                "12345",
                "12345",
                "12345",
                true
        );

        Assertions.assertTrue(
                signUpPage.isWrongFormInputErrorMessageDisplayed()
        );

        Assertions.assertTrue(
                signUpPage.isWrongEmailMessageDisplayed()
        );
    }

    @Test
    public void existingEmailTest(){
        signUpPage.login(
                "12345",
                "evorovarvara7@gmail.com",
                "12345",
                true
        );

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//        wait.until(driver ->
//                signUpPage.isWrongFormInputErrorMessageDisplayed()
//        );
//
//        wait.until(driver ->
//                signUpPage.isWrongEmailMessageDisplayed()
//        );
//
        Assertions.assertTrue(
                signUpPage.isWrongFormInputErrorMessageDisplayed()
        );
        System.out.println(driver.getPageSource());
        Assertions.assertTrue(
                signUpPage.isWrongEmailMessageDisplayed()
        );
    }

    @Test
    public void emptyPasswordTest(){
        signUpPage.login(
                "12345",
                "evorovarvara7@gmail.com",
                "",
                true
        );

        Assertions.assertTrue(
                signUpPage.isWrongFormInputErrorMessageDisplayed()
        );
        Assertions.assertTrue(
                signUpPage.isWrongFormInputErrorMessageDisplayed()
        );
    }

    @Test
    public void shortPasswordTest(){
        signUpPage.login(
                "12345",
                "evorovarvara7@gmail.com",
                "123",
                true
        );

        Assertions.assertTrue(
                signUpPage.isWrongFormInputErrorMessageDisplayed()
        );

        Assertions.assertTrue(
                signUpPage.isWrongPasswordErrorMessageDisplayed()
        );
    }

    @Test
    public void unclickedCheckboxTest(){
        signUpPage.login(
                "12345",
                "evorovarvara7@gmail.com",
                "123",
                false
        );

        Assertions.assertTrue(
                signUpPage.isWrongFormInputErrorMessageDisplayed()
        );

        Assertions.assertTrue(
                signUpPage.isEmptyCheckboxErrorMessageDisplayed()
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
