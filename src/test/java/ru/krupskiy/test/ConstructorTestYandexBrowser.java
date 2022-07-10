package ru.krupskiy.test;

import com.codeborne.selenide.Selenide;
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

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static org.junit.Assert.assertTrue;


public class ConstructorTestYandexBrowser {

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver","/home/akrupskiy/IdeaProjects/Diplom_3/yandexdriver-22.5.0.1879-linux/yandexdriver");
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);
    }

    @After
    public void tearDown() {
        close();
    };

    @Test
    @DisplayName("Успешный переход между вкладками раздела 'Конструктор'. Yandex_browser")
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
