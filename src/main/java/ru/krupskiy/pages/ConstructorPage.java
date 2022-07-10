package ru.krupskiy.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

public class ConstructorPage {

    //локатор кнопки "Соберите бургер"
    @FindBy(how = How.XPATH, using = ".//h1[(text()[contains(.,'Соберите бургер')])]")
    private SelenideElement collectBurgerText;
    //локатор вкладки "Соусы"
    @FindBy(how = How.XPATH, using = ".//span[(text()[contains(.,'Соусы')])]")
    private SelenideElement sauceTab;
    //локатор вкладки "Начинки"
    @FindBy(how = How.XPATH, using = ".//span[(text()[contains(.,'Начинки')])]")
    private SelenideElement ingredientTab;
    //локатор вкладки "Булки"
    @FindBy(how = How.XPATH, using = ".//span[(text()[contains(.,'Булки')])]")
    private SelenideElement bunTab;
    //локатор раздела "Начинки" в меню
    @FindBy(how = How.XPATH, using = ".//h2[(text()[contains(.,'Начинки')])]")
    private SelenideElement ingredientMenu;
    //локатор раздела "Соусы" в меню
    @FindBy(how = How.XPATH, using = ".//h2[(text()[contains(.,'Соусы')])]")
    private SelenideElement sauceMenu;
    //локатор раздела "Булки" в меню
    @FindBy(how = How.XPATH, using = ".//h2[(text()[contains(.,'Булки')])]")
    private SelenideElement bunMenu;

    @Step("нажатие на вкладку 'Соусы'")
    public void clickSauceTab() {
        this.sauceTab.click();
    }

    @Step("нажатие на вкладку 'Начинки'")
    public void clickIngredientTab() {
        this.ingredientTab.click();
    }

    @Step("нажатие на вкладку 'Булки'")
    public void clickBunTab() {
        this.bunTab.click();
    }

    @Step("возвращает true, если отображается текст 'Соберите бургер' на странице личного кабинета")
    public boolean isCollectBurgerTextDisplayed() {
        return this.collectBurgerText.isDisplayed();
    }

    @Step("возвращает true, если отображается содержимое вкладки 'Соусы'")
    public boolean areSaucesDisplayed() {
        return this.sauceMenu.isDisplayed();
    }

    @Step("возвращает true, если отображается содержимое вкладки 'Начинки'")
    public boolean areIngredientsDisplayed() {
        return this.ingredientMenu.isDisplayed();
    }

    @Step("возвращает true, если отображается содержимое вкладки 'Булочки'")
    public boolean areBunsDisplayed() {
        return this.bunMenu.isDisplayed();
    }


}
