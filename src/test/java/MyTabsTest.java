import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.AuthPage;
import org.example.pages.MyTabsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyTabsTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        login();
    }

    @Test
    public void createPlaylistTest() {
        driver.get("https://www.songsterr.com/playlists");
        MyTabsPage tabPage = new MyTabsPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("xDtWnW_header")
        ));

        WebElement createPlaylistButton = wait.until(ExpectedConditions.elementToBeClickable(tabPage.createPlaylistButton));
        createPlaylistButton.click();

        tabPage.typeName("qwerty");
        WebElement playlistNameField = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(tabPage.playlistNameField));
        playlistNameField.sendKeys(Keys.ENTER);

        WebElement playlistList = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(tabPage.playlistList));
        Assertions.assertTrue(playlistList.getText().contains("qwerty"));
    }

    @Test
    public void addToPlaylistTest() {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait1.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
        driver.get("https://www.songsterr.com/favorites");
        MyTabsPage tabPage = new MyTabsPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("xDtWnW_header")
        ));
        wait.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );

        WebElement song = wait.until(
                ExpectedConditions.presenceOfElementLocated(tabPage.songName)
        );
        String songName = song.getText();
        System.out.println(songName);
        WebElement addToPlaylistButton = wait.until(ExpectedConditions.presenceOfElementLocated(tabPage.addToPlaylistButton));
        addToPlaylistButton.click();

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(tabPage.dropdown));
        WebElement playlist = dropdown.findElement(
                By.xpath(".//button[contains(@class,'dropdownDelete')]")
        );
        playlist.click();


        driver.get("https://www.songsterr.com/playlists");

        WebElement playlistSongs = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(tabPage.playlistSongs));

        Assertions.assertTrue(playlistSongs.getText().contains(songName));
    }

    @Test
    public void deleteFromPlaylistTest() {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait1.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
        driver.get("https://www.songsterr.com/playlists");
        MyTabsPage tabPage = new MyTabsPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("xDtWnW_header")
        ));
        wait.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );

        WebElement addToPlaylistButton = wait.until(ExpectedConditions.presenceOfElementLocated(tabPage.addToPlaylistButton));
        addToPlaylistButton.click();

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(tabPage.dropdown));
        WebElement deleteButton = dropdown.findElement(
                By.xpath(".//button[contains(@class,'dropdownDelete')]")
        );
        deleteButton.click();

        WebElement stub = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(tabPage.stub));

        Assertions.assertTrue(stub.getText().contains("Плейлист пуст"));
    }

    @Test
    public void searchInPlaylistTest() {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait1.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
        driver.get("https://www.songsterr.com/playlists");
        MyTabsPage tabPage = new MyTabsPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("xDtWnW_header")
        ));
        WebElement song = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(tabPage.songName));
        String songName = song.getText();

        tabPage.searchSong(songName);

        WebElement playlistSongs = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(tabPage.playlistSongs));

        Assertions.assertTrue(playlistSongs.getText().contains(songName));
    }

    @Test
    public void searchInFavoritesTest() {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait1.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
        driver.get("https://www.songsterr.com/favorites");
        MyTabsPage tabPage = new MyTabsPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("xDtWnW_header")
        ));
        wait.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );

        WebElement song = wait.until(
                ExpectedConditions.presenceOfElementLocated(tabPage.songName)
        );
        String songName = song.getText();
        System.out.println(songName);

        tabPage.searchSong(songName);

        WebElement favoriteSongs = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(tabPage.favoritesList));

        Assertions.assertTrue(favoriteSongs.getText().contains(songName));
    }

    @Test
    public void deletePlaylistTest() {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait1.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
        driver.get("https://www.songsterr.com/playlists");
        MyTabsPage tabPage = new MyTabsPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("xDtWnW_header")
        ));
        wait.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );

        WebElement deleteButton = wait.until(
                ExpectedConditions.elementToBeClickable(tabPage.deletePlaylistButton)
        );
        deleteButton.click();
        deleteButton.click();

        WebElement stub = wait.until(
                ExpectedConditions.presenceOfElementLocated(tabPage.stub)
        );

        Assertions.assertTrue(stub.getText().contains("У вас нет плейлистов"));
    }

    @Test
    public void renamePlaylistTest() {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait1.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
        driver.get("https://www.songsterr.com/playlists");
        MyTabsPage tabPage = new MyTabsPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("xDtWnW_header")
        ));
        wait.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );

        WebElement playlist = wait.until(
                ExpectedConditions.presenceOfElementLocated(tabPage.playlistName)
        );
        String oldPlaylistName = playlist.getText();

        WebElement renameButton = wait.until(
                ExpectedConditions.presenceOfElementLocated(tabPage.renamePlaylistButton)
        );
        renameButton.click();

        tabPage.typeName("qwerty");
        WebElement playlistNameField = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(tabPage.playlistNameField));
        playlistNameField.sendKeys(Keys.ENTER);

//        WebElement playlist = wait.until(
//                ExpectedConditions.presenceOfElementLocated(tabPage.playlistName)
//        );
        String newPlaylistName = playlist.getText();

        Assertions.assertNotEquals(oldPlaylistName, newPlaylistName);
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