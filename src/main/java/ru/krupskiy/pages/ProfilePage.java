package ru.krupskiy.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

public class ProfilePage {
    //локатор кнопки "Профиль"
    @FindBy(how = How.XPATH, using = ".//a[(text()[contains(.,'Профиль')])]")
    private SelenideElement profileButton;
    //локатор кнопки "Конструктор"
    @FindBy(how = How.XPATH, using = ".//p[(text()[contains(.,'Конструктор')])]")
    private SelenideElement constructorButton;
    //локатор кнопки "Выход"
    @FindBy(how = How.XPATH, using = ".//button[(text()[contains(.,'Выход')])]")
    private SelenideElement exitButton;
    //локатор логотипа Stellar Burgers
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement logo;

    @Step("нажатие на кнопку 'Профиль'")
    public void clickProfileButton() {
        this.profileButton.click();
    }
    @Step("нажатие на кнопку 'Конструктор'")
    public void clickConstructorButton() {
        this.constructorButton.click();
    }
    @Step("нажатие на логотип Stellar Burgers'")
    public void clickOnLogoButton() {
        this.logo.click();
    }
    @Step("нажатие на кнопку 'Выход'")
    public void clickOnExitButton() {
        this.exitButton.click();
    }
    @Step("возвращает true, если отображается текст 'Профиль' на странице личного кабинета")
    public boolean isProfileTextDisplayed() {
        return this.profileButton.isDisplayed();
    }
}
