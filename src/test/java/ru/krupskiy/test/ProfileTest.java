package ru.krupskiy.test;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.krupskiy.pages.ConstructorPage;
import ru.krupskiy.pages.LoginPage;
import ru.krupskiy.pages.MainPage;
import ru.krupskiy.pages.ProfilePage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class ProfileTest {

    @After
    public void tearDown() {
        close();
    };

    @Test
    @DisplayName("Успешный переход по клику на 'Личный кабинет'. Google_chrome")
    public void enterProfilePage() {
        String email = "sdvvddva2@krupsyan.ru";
        String password = "123456";
        String url = "https://stellarburgers.nomoreparties.site/";

        MainPage mainPage = open(url, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        mainPage.clickProfileButton();

        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickProfileButton();
        assertTrue(profilePage.isProfileTextDisplayed());
    }

    @Test
    @DisplayName("Успешный переход по клику на 'Конструктор' из личного кабинета. Google_chrome")
    public void enterConstructorViaProfilePage() {
        String email = "sdvvddva2@krupsyan.ru";
        String password = "123456";
        String url = "https://stellarburgers.nomoreparties.site/";

        MainPage mainPage = open(url, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        mainPage.clickProfileButton();

        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickConstructorButton();
        ConstructorPage constructorPage = page(ConstructorPage.class);
        assertTrue(constructorPage.isCollectBurgerTextDisplayed());
    }

    @Test
    @DisplayName("Успешный переход на главную по клику на логотип Stellar Burgers из личного кабинета. Google_chrome")
    public void clickOnLogoViaProfilePage() {
        String email = "sdvvddva2@krupsyan.ru";
        String password = "123456";
        String url = "https://stellarburgers.nomoreparties.site/";

        MainPage mainPage = open(url, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        mainPage.clickProfileButton();

        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickOnLogoButton();
        assertTrue(mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Успешный выход из аккаунта. Google_chrome")
    public void exitProfile() {
        String email = "sdvvddva2@krupsyan.ru";
        String password = "123456";
        String url = "https://stellarburgers.nomoreparties.site/";

        MainPage mainPage = open(url, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        mainPage.clickProfileButton();

        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickOnExitButton();

        loginPage.clickLoginButton();
        assertTrue(loginPage.isLoginButtonDisplayed());
    }
}
