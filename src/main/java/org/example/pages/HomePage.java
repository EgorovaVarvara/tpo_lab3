package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By searchInput = By.xpath("//*[@id=\"search-wrap\"]/div/div[1]/div/input");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.songsterr.com/");
    }

    public void acceptCookiesIfPresent() {
        By cookieButton = By.xpath("//button[contains(.,'Accept') or contains(.,'I agree')]");
        try {
            click(cookieButton);
        } catch (Exception ignored) {}
    }

    public void searchSong(String songName) {
        type(searchInput, songName);
    }
}