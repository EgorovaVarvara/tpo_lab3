package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegPage extends BasePage{

    private final By usernameInput =
            By.xpath("//input[@name='name']");

    private final By emailInput =
            By.xpath("//input[@type='email']");

    private final By passwordInput =
            By.xpath("//input[@type='password']");

    private final By privacyCheckbox = By.xpath("//input[@name='terms']");

    private final By emptyInputErrorMessage =
            By.xpath("//*[contains(text(),'empty') or contains(text(),'пустым')]");

    private final By wrongFormInputErrorMessage =
            By.xpath("//*[contains(text(),'Пожалуйста, исправьте ошибки и повторите попытку')]");

    private final By wrongEmailErrorMessage =
            By.xpath("//*[contains(.,'Неверный адрес электронной почты') or contains(.,'уже существует')]");

    private final By wrongPasswordErrorMessage =
            By.xpath("//*[contains(text(),'Должно быть не менее 5 символов')]");

    private final By emptyCheckboxErrorMessage =
            By.xpath("//*[contains(text(),'Должно быть отмечено')]");

    private final By signInLink =
            By.xpath("//a[contains(@href,'signin')]");

    private final By signUpButton =
            By.xpath("//button[@type='submit']");

    public RegPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.songsterr.com/signup");
    }

    public void enterName(String name){
        type(usernameInput, name);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    protected void jsClick(By locator) {
        WebElement el = find(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", el);
    }

    public void clickCheckbox(boolean click){
        if (click) jsClick(privacyCheckbox);
    }

    public void clickSignUp() {
        click(signUpButton);
    }

    public void login(String name, String email, String password, boolean click) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickCheckbox(click);
        clickSignUp();
    }

    public void goToSignIn() {
        click(signInLink);
    }

    public boolean isEmptyInputErrorMessageDisplayed(){
        return isDisplayed(emptyInputErrorMessage);
    }

    public boolean isEmptyCheckboxErrorMessageDisplayed(){
        return isDisplayed(emptyCheckboxErrorMessage);
    }

    public boolean isWrongEmailMessageDisplayed(){
        return isDisplayed(wrongEmailErrorMessage);
    }

    public boolean isWrongPasswordErrorMessageDisplayed(){
        return isDisplayed(wrongPasswordErrorMessage);
    }

    public boolean isWrongFormInputErrorMessageDisplayed() {
        return isDisplayed(wrongFormInputErrorMessage);
    }
}
