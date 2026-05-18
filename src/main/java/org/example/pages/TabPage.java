package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TabPage extends BasePage {

    private final By tabTitle = By.xpath("//span[contains(@class,'title')]");
    private final By tabArtist = By.xpath("//h2[contains(@class,'artist')]");
    private final By tabTablature = By.xpath("//section[contains(@class,'tablature')]");
    private final By tabControls = By.xpath("//div[contains(@class,'controls')]");

    public  final By playButton = By.xpath("//button[contains(@title,'Воспроизведение')]");
    public  final  By pauseButton = By.xpath("//button[contains(@title,'Пауза')]");

    public final By mixerControls = By.xpath("//button[@id='control-mixer']");
    public final By formatControls = By.xpath("//button[@name='display-mode']");
    public final By formatType = By.xpath("//a[contains(@role,'option')]");
    public final By favoriteButton = By.xpath("//button[@id='favorite-toggle']");
    public final By myTabs = By.xpath("//a[contains(@id,'menu-favorites')]");

    // вынести в отдельную страницу
    public final By favoritesList = By.xpath("//div[contains(@class,'favorites')]");
    public final By createPlaylistButton = By.xpath("//button[@id='create-playlist']");
    public final By playlistNameField = By.xpath("//textarea[contains(@class, 'playlistEdit')]");
    public final By playlistList = By.xpath("//aside[@id='playlist-menu']");
    public final By favoriteSongName = By.xpath("//div[contains(@class, 'name')]");
    public final By addToPlaylistButton = By.xpath("//button[contains(@class, 'dots')]");
    public final By dropdown = By.xpath("//div[contains(@class, 'dropdown')]");
    public final By playlistSongs = By.xpath("//div[contains(@class,'favorites')]");
    public final By stub = By.xpath("//div[contains(@class,'stub')]");

    public TabPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(d ->
                !d.findElements(tabTitle).isEmpty()
                        && !d.findElements(tabArtist).isEmpty()
                        && !d.findElements(tabTablature).isEmpty()
                        && !d.findElements(tabControls).isEmpty()
        );
    }

    public void typeName(String name) {
        type(playlistNameField, name);
    }

}