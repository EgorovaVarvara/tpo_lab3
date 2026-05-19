package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MyTabsPage extends BasePage {

    public final By createPlaylistButton = By.xpath("//button[@id='create-playlist']");
    public final By playlistNameField = By.xpath("//textarea[contains(@class, 'playlistEdit')]");
    public final By playlistList = By.xpath("//aside[@id='playlist-menu']");
    public final By addToPlaylistButton = By.xpath("//button[contains(@class, 'dots')]");
    public final By dropdown = By.xpath("//div[contains(@class, 'dropdown')]");
    public final By playlistSongs = By.xpath("//div[contains(@class,'favorites')]");
    public final By stub = By.xpath("//div[contains(@class,'stub')]");
    public final By songName = By.xpath("//div[contains(@class,'name')]");
    public final By searchInput = By.xpath("//input[@type='text']");
    public final By favoritesList = By.xpath("//div[contains(@class,'favorites')]");

    public final By playlistName = By.xpath("//button[contains(@class, 'name')]");
    public final By deletePlaylistButton = By.xpath("//button[contains(@class, 'remove')]");
    public final By renamePlaylistButton = By.xpath("//button[contains(@class, 'rename')]");

    public MyTabsPage(WebDriver driver) {
        super(driver);
    }

    public void typeName(String name) {
        type(playlistNameField, name);
    }

    public void searchSong(String songName) {
        type(searchInput, songName);
    }
}