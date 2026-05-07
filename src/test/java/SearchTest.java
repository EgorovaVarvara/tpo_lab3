import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void searchValidInputTest() {
        HomePage homePage = new HomePage(driver);

        homePage.open();
        homePage.acceptCookiesIfPresent();
        homePage.searchSong("Metallica Nothing Else Matters");

        Assertions.assertTrue(driver.getPageSource().contains("Metallica"));
    }

    @Test
    public void searchErrorInputTest() {
        HomePage homePage = new HomePage(driver);

        homePage.open();
        homePage.acceptCookiesIfPresent();
        homePage.searchSong("Metallika");

        Assertions.assertTrue(driver.getPageSource().contains("Metallica"));
    }

    @Test
    public void searchInvalidInputTest() {
        HomePage homePage = new HomePage(driver);

        homePage.open();
        homePage.acceptCookiesIfPresent();
        homePage.searchSong("dfxgchjhkjl");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(homePage.noResultsMessage));

        Assertions.assertTrue(
                driver.findElements(homePage.noResultsMessage).size() > 0
        );
    }

    @Test
    public void searchWithFiltersTest() {
        HomePage homePage = new HomePage(driver);

        homePage.open();
        homePage.acceptCookiesIfPresent();


        By firstResult = By.xpath("(//div[@data-field='name'])[1]");
        String oldFirstResult = driver.findElement(firstResult).getText();

        homePage.setFilters();
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(driver ->
                !driver.findElement(firstResult).getText().equals(oldFirstResult)
        );

        Assertions.assertTrue(
                driver.findElement(firstResult).getText().contains("End of You")
        );
    }

    @Test
    public void searchWithNothingTest() {
        HomePage homePage = new HomePage(driver);

        homePage.open();
        homePage.acceptCookiesIfPresent();


        By firstResult = By.xpath("(//div[@data-field='name'])[1]");
        String oldFirstResult = driver.findElement(firstResult).getText();


        homePage.searchSong("");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver ->
                        driver.findElement(firstResult).getText().equals(oldFirstResult)
                );

        Assertions.assertEquals(
                driver.findElement(firstResult).getText(),
                oldFirstResult
        );
    }



    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}