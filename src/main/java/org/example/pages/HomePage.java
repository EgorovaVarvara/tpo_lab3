package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By searchInput = By.xpath("//input[@type='text']");

    public final By noResultsMessage = By.xpath("//*[contains(text(),'Табы не найдены')]");

    private final By filterByInstr = By.xpath("//button[@name='filterByInstrumentSelect']");
    private final By filterByDif = By.xpath("//button[@name='filterByDifficultySelect']");
    private final By filterInstOption = By.xpath("//li[@id='filterByInstrumentSelect-button-option-1']");
    private final By filterDifOption = By.xpath("//li[@id='filterByDifficultySelect-button-option-1']");

    private final By signInButton = By.xpath("//a[contains(@href,'signin')]");

    private final By firstTab = By.xpath("(//div[@data-field='name'])[1]");



    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.songsterr.com/");
    }

    public void acceptCookiesIfPresent() {
        By cookieButton = By.xpath("//button[contains(.,'Принять все')]");
        try {
            click(cookieButton);
        } catch (Exception ignored) {}
    }

    public void searchSong(String songName) {
        type(searchInput, songName);
    }

    public void setFilters() {
        searchSong("pp");
        click(filterByInstr);
        click(filterInstOption);
        click(filterByDif);
        click(filterDifOption);
    }

    public  void navigate() {
        click(signInButton);
    }

    public void clickTo(By locator) {
        click(locator);
    }

    public void openTab() {
        click(firstTab);
    }

}