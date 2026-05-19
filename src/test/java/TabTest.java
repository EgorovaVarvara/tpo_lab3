import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.AuthPage;
import org.example.pages.HomePage;
import org.example.pages.TabPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TabTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void openTabTest() {
        HomePage homePage = new HomePage(driver);

        homePage.open();
        homePage.acceptCookiesIfPresent();

        By firstResult = By.xpath("(//div[@data-field='name'])[1]");
        String name = driver.findElement(firstResult).getText();

        homePage.openTab();

        By tabTitle = By.xpath("//span[contains(@class,'songTitle')]");
        String title = driver.findElement(tabTitle).getText();
        TabPage tabPage = new TabPage(driver);

        Assertions.assertTrue(tabPage.isLoaded());
        Assertions.assertEquals(name, title);
    }

    @Test
    public void playTabTest() {
        HomePage homePage = new HomePage(driver);

        homePage.open();
        homePage.acceptCookiesIfPresent();

        homePage.openTab();

        TabPage tabPage = new TabPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(tabPage.playButton))
                .click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(tabPage.pauseButton));

        Assertions.assertTrue(
                driver.findElements(tabPage.pauseButton).size() > 0
        );
    }

    //не работаетб не находит почему то control-mixer
    @Test
    public void changeTrackTest() {
        HomePage homePage = new HomePage(driver);

        homePage.open();
        homePage.acceptCookiesIfPresent();
        homePage.openTab();

        By mixerControls = By.id("control-mixer");

        WebElement mixer = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(mixerControls));

        mixer.click();

        By activeTrack = By.xpath("//div[contains(@class,'itemActive')]");
        String oldTrack = driver.findElement(activeTrack).getText();

        By differentTrack = By.xpath("(//div[contains(@id,'mixer-item')])[1]");
        driver.findElement(differentTrack).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> !d.findElement(activeTrack).getText().equals(oldTrack));

        String newTrack = driver.findElement(activeTrack).getText();

        Assertions.assertNotEquals(oldTrack, newTrack);
    }


    @Test
    public void changeFormatTest() {
        HomePage homePage = new HomePage(driver);

        homePage.open();
        homePage.acceptCookiesIfPresent();
        homePage.openTab();

        TabPage tabPage = new TabPage(driver);

        WebElement mixer = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(tabPage.formatControls));

        mixer.click();
        WebElement oldTab = driver.findElement(By.xpath("//span[contains(@class,'instrumentTab')]"));
        String oldTabType = oldTab.getText();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(tabPage.formatType))
                .click();

        WebElement newTab = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[contains(@class,'instrumentTab')]")));

        String newTabType = newTab.getText();

        Assertions.assertNotEquals(oldTabType, newTabType);
    }

    @Test
    public void toFavouritesTest() {
        login();
        TabPage tabPage = new TabPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("xDtWnW_header")
        ));

        WebElement favoriteButton = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(tabPage.favoriteButton));

        favoriteButton.click();

        By tabTitle = By.xpath("//span[contains(@class,'songTitle')]");
        String title = driver.findElement(tabTitle).getText();

        WebElement myTabs = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(tabPage.myTabs));

        myTabs.click();
        WebElement favoritesList = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(tabPage.favoritesList));
        Assertions.assertTrue(favoritesList.getText().contains(title));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void login() {
        driver.get("https://www.songsterr.com/signin");
        AuthPage authPage = new AuthPage(driver);

        authPage.login("tashkinova.2017@inbox.ru", "qwerty");
    }
}