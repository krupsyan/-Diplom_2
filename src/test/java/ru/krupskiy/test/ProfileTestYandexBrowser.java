package ru.krupskiy.test;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.krupskiy.pages.ConstructorPage;
import ru.krupskiy.pages.LoginPage;
import ru.krupskiy.pages.MainPage;
import ru.krupskiy.pages.ProfilePage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static org.junit.Assert.assertTrue;

public class ProfileTestYandexBrowser {

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver","/home/akrupskiy/IdeaProjects/Diplom_3/yandexdriver-22.5.0.1879-linux/yandexdriver"); WebDriver driver =new ChromeDriver(); setWebDriver(driver);
    }

    @After
    public void tearDown() {
        close();
    };

    @Test
    @DisplayName("Успешный переход по клику на 'Личный кабинет'. Yandex_browser")
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
    @DisplayName("Успешный переход по клику на «Конструктор» из личного кабинета. Yandex_browser")
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
    @DisplayName("Успешный переход на главную по клику на логотип Stellar Burgers из личного кабинета. Yandex_browser")
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
    @DisplayName("Успешный выход из аккаунта. Yandex_browser")
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
