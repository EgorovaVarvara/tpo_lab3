package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage{

    private final By accountButton =
            By.xpath("//a[@id='menu-account']");

    private final By logoutButton =
            By.xpath("//button[contains(.,'Выйти')]");

    private final By plusButton =
            By.xpath("//div[@id='account-meta']//a[@href='/plus']");

    private final By changeNameButton =
            By.xpath("//a[contains(@href,'name') and contains(text(), 'Изменить имя')]");

    private final By newNameInput =
            By.xpath("//input[@name='name']");

    private final By changeNameSubmitButton =
            By.xpath("//button[@type='submit']");

    private final By changeNameMessage =
            By.xpath("//h4[contains(., 'изменено')]");

    private final By changeEmailButton =
            By.xpath("//a[contains(@href,'help') and contains(text(), 'Изменить электронную почту')]");

    private final By changePasswordButton =
            By.xpath("//a[contains(@href,'password') and contains(text(), 'Изменить пароль')]");

    private final By changePasswordMessage =
            By.xpath("//h4[contains(., 'изменён')]");

    private final By privacyButton =
            By.xpath("//a[contains(@href,'privacy')]");

    private final By termsButton =
            By.xpath("//a[contains(@href,'terms')]");

    private final By wrongFormInputErrorMessage =
            By.xpath("//*[contains(text(),'повторите попытку')]");

    private final By passwordInput =
            By.xpath("//input[@name='password']");

    private final By passwordAgainInput =
            By.xpath("//input[@name='passwordAgain']");

    private final By wrongPasswordErrorMessage =
            By.xpath("//*[contains(text(),'не менее 5 символов') or contains(text(), 'пустым')]");

    private final By unmatchedPasswordsErrorMessage =
            By.xpath("//*[contains(text(),'совпадать')]");

    private final By changePasswordSubmitButton =
            By.xpath("//button[@type='submit']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }
    public void open() {
        driver.get("https://www.songsterr.com/account");
    }

    private void enterPassword(String password){
        type(passwordInput, password);
    }

    private void enterAgainPassword(String password){
        type(passwordAgainInput, password);
    }

    public void changePassword(String password, String passwordAgain){
        click(changePasswordButton);
        enterPassword(password);
        enterAgainPassword(passwordAgain);
        click(changePasswordSubmitButton);
    }

    public void logout(){
        click(accountButton);
        click(logoutButton);
    }

    public void clickPlus(){
        click(plusButton);
    }

    public void changeName(String name){
        click(changeNameButton);
        type(newNameInput, name);
    }

    public void submitChangeName(){
        click(changeNameSubmitButton);
    }

    public boolean ifSubmitNameButtonEnabled(){
        return find(changeNameSubmitButton).isEnabled();
    }

    public void changeEmail(){
        click(changeEmailButton);
    }

    public void openPrivacy(){
        click(privacyButton);
    }

    public void openTerms(){
        click(termsButton);
    }

    public boolean isChangeNameMessageDisplayed(){
        return isDisplayed(changeNameMessage);
    }

    public boolean isChangePasswordMessageDisplayed(){
        return isDisplayed(changePasswordMessage);
    }

    public boolean isWrongFormInputMessageDisplayed(){
        return isDisplayed(wrongFormInputErrorMessage);
    }

    public boolean isWrongPasswordErrorMessageDisplayed(){
        return isDisplayed(wrongPasswordErrorMessage);
    }

    public boolean isUnmatchedPasswordsErrorMessage(){
        return isDisplayed(unmatchedPasswordsErrorMessage);
    }
}
