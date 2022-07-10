package ru.krupskiy.test;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.krupskiy.pages.ConstructorPage;
import ru.krupskiy.pages.LoginPage;
import ru.krupskiy.pages.MainPage;
import ru.krupskiy.pages.ProfilePage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    @Test
    @DisplayName("Успешный переход между вкладками раздела 'Конструктор'. Google_chrome")
    public void enterConstructorViaProfilePage() {
        String email = "sdvvddva2@krupsyan.ru";
        String password = "123456";
        String url = "https://stellarburgers.nomoreparties.site/";

        MainPage mainPage = Selenide.open(url, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        mainPage.clickProfileButton();

        ProfilePage profilePage = Selenide.page(ProfilePage.class);
        profilePage.clickConstructorButton();
        ConstructorPage constructorPage = Selenide.page(ConstructorPage.class);

        constructorPage.clickSauceTab();
        assertTrue(constructorPage.areSaucesDisplayed());

        constructorPage.clickIngredientTab();
        assertTrue(constructorPage.areIngredientsDisplayed());

        constructorPage.clickBunTab();
        assertTrue(constructorPage.areBunsDisplayed());
    }
}
