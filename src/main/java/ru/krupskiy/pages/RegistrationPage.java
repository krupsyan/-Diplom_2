package ru.krupskiy.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

public class RegistrationPage {

    //локатор поля ввода имени
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement NameField;
    //локатор поля ввода email
    @FindBy(how = How.XPATH, using = ".//input[@value='']")
    private SelenideElement EmailField;
    //локатор поля ввода пароля
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement PasswordField;
    //Локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//button[(text()[contains(.,'Зарегистрироваться')])]")
    private SelenideElement registerButton;
    //Локатор сообщения об ошибке "Некорректный пароль"
    @FindBy(how = How.XPATH, using = ".//p[(text()[contains(.,'Некорректный пароль')])]")
    private SelenideElement incorrectPasswordMessage;
    //локатор ссылки "Войти"
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement loginLink;

    @Step("заполняет поле ввода имени")
    public void setName(String name) {

        this.NameField.sendKeys(name);
    }

    @Step("заполняет поле ввода email")
    public void setEmail(String email) {
        this.EmailField.sendKeys(email);
    }

    @Step("заполняет поле ввода пароля")
    public void setPassword(String password) {

        this.PasswordField.sendKeys(password);
    }

    @Step("нажатие на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {

        this.registerButton.click();
    }
    @Step("нажатие на ссылку 'Войти'")
    public void clickLoginLink() {

        this.loginLink.click();
    }

    @Step("возвращает true, если отображается сообщение об ошибке 'Некорректный пароль'")
    public boolean isIncorrectPasswordMessageDisplayed() {
        return this.incorrectPasswordMessage.isDisplayed();
    }


}
