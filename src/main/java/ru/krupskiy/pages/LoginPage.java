package ru.krupskiy.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

public class LoginPage {

    //локатор поля ввода email
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement emailField;
    //локатор поля ввода пароля
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement passwordField;
    //локатор ссылки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//a[(text()[contains(.,'Зарегистрироваться')])]")
    private SelenideElement registerLink;
    //Локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = ".//button[(text()[contains(.,'Войти')])]")
    private SelenideElement loginButton;
    //Локатор формы авторизации
    @FindBy(how = How.CLASS_NAME, using = "Auth_login__3hAey")
    private SelenideElement loginForm;
    //локатор ссылки "Восстановить пароль"
    @FindBy(how = How.XPATH, using = ".//a[(text()[contains(.,'Восстановить пароль')])]")
    private SelenideElement forgetPasswordLink;

    @Step("заполняет поле ввода email")
    public void setEmail(String email) {
        emailField.sendKeys(email);
    }

    @Step("заполняет поле ввода пароля")
    public void setPassword(String password) {
        passwordField.sendKeys(password);
    }

    @Step("нажатие на ссылку 'Зарегистрироваться'")
    public void clickRegisterLink() {
        registerLink.click();
    }

    @Step("нажатие на кнопку 'Войти'")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("нажатие на ссылку 'Восстановить пароль'")
    public void clickForgetPasswordLink() {
        forgetPasswordLink.click();
    }

    @Step("возвращает true, если отображается кнопка 'Войти' на странице авторизации")
    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    @Step("возвращает true, если отображается форма авторизации")
    public boolean isLoginFormDisplayed() {
        return loginForm.isDisplayed();
    }

    @Step("авторизация")
    public void authorize(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }
}
