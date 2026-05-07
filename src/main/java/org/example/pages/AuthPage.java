package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthPage extends BasePage{

    private final By emailInput =
            By.xpath("//input[@type='email']");

    private final By passwordInput =
            By.xpath("//input[@type='password']");

    private final By signInButton =
            By.xpath("//button[@type='submit']");

    private final By signUpLink =
            By.xpath("//a[contains(@href,'signup')]");

    private final By forgotPasswordLink = By.xpath("//a[contains(@href, 'forgot')]");

    private final By privacyLink = By.xpath("//a[contains(@href,'privacy')]");

    private final By termsLink = By.xpath("//a[contains(@href,'terms')]");

    private final By errorMessage =
            By.xpath("//*[contains(text(),'Invalid') or contains(text(),'incorrect') or contains(text(),'Неверный')]");

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.songsterr.com/signin");
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickSignIn() {
        click(signInButton);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
    }

    public void goToSignUp() {
        click(signUpLink);
    }

    public void goToForgotPassPage(){
        click(forgotPasswordLink);
    }

    public void goToPrivacy(){
        click(privacyLink);
    }

    public void goToTerms(){
        click(termsLink);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

}
