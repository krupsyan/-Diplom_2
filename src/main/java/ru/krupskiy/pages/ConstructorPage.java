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
    @FindBy(how = How.XPATH, using = ".//span[(text()[contains(.,'Соусы')])]/parent::*")
    private SelenideElement sauceTab;
    //локатор вкладки "Начинки"
    @FindBy(how = How.XPATH, using = ".//span[(text()[contains(.,'Начинки')])]/parent::*")
    private SelenideElement ingredientTab;
    //локатор вкладки "Булки"
    @FindBy(how = How.XPATH, using = ".//span[(text()[contains(.,'Булки')])]/parent::*")
    private SelenideElement bunTab;

    @Step("нажатие на вкладку 'Соусы'")
    public void clickSauceTab() {
        sauceTab.click();
    }

    @Step("нажатие на вкладку 'Начинки'")
    public void clickIngredientTab() {
        ingredientTab.click();
    }

    @Step("нажатие на вкладку 'Булки'")
    public void clickBunTab() {
        bunTab.click();
    }

    @Step("возвращает true, если отображается текст 'Соберите бургер' на странице личного кабинета")
    public boolean isCollectBurgerTextDisplayed() {
        return collectBurgerText.isDisplayed();
    }

    @Step("возвращает true, если вкладка меню 'Соусы' активна")
    public boolean isSauceTabActive() {
        return sauceTab.getAttribute("class").contains("tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect");
    }
    @Step("возвращает true, если вкладка меню 'Соусы' активна")
    public boolean isIngredientTabActive() {
        return ingredientTab.getAttribute("class").contains("tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect");
    }
    @Step("возвращает true, если вкладка меню 'Соусы' активна")
    public boolean isBunTabActive() {
        return bunTab.getAttribute("class").contains("tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect");
    }
}
