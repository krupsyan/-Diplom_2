package ru.krupskiy.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class MainPage {

    public static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";

    //Локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement loginButton;
    //Локатор кнопки "Личный кабинет"
    @FindBy(how = How.XPATH, using = ".//p[(text()[contains(.,'Личный Кабинет')])]")
    private SelenideElement profileButton;
    //Кнопка "Оформить заказ"
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement makeOrderButton;

    @Step("нажатие на кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("нажатие на кнопку 'Личный кабинет'")
    public void clickProfileButton() {
        profileButton.click();
    }
    @Step("возвращает true, если отображается кнопка 'Оформить заказ'")
    public boolean isMakeOrderButtonDisplayed() {
        makeOrderButton.shouldBe(visible);
        return makeOrderButton.isDisplayed();
    }
}
