package ru.krupskiy.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class MainPage {

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
        this.loginButton.click();
    }

    @Step("нажатие на кнопку 'Личный кабинет'")
    public void clickProfileButton() {
        this.profileButton.click();
    }
    @Step("возвращает true, если отображается кнопка 'Оформить заказ'")
    public boolean isMakeOrderButtonDisplayed() {
        makeOrderButton.shouldBe(visible);
        return this.makeOrderButton.isDisplayed();
    }
}
