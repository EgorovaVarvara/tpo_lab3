import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void navigateBackTest() {
        HomePage homePage = new HomePage(driver);

        homePage.open();
        homePage.acceptCookiesIfPresent();
        homePage.navigate();
        driver.navigate().back();

        Assertions.assertTrue(driver.getPageSource().contains("Искать табы"));
    }

    @Test
    public void openExistingTabByUrlTest() {
        driver.get("https://www.songsterr.com/a/wsa/tab-s922");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver ->
                        webDriver.getTitle() != null && !webDriver.getTitle().isEmpty()
                );

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("tab-s922")
        );
        Assertions.assertTrue(driver.getPageSource().contains("De Profundis"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}