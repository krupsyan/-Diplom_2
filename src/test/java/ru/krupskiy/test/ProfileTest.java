package ru.krupskiy.test;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.krupskiy.api.User;
import ru.krupskiy.api.UserClient;
import ru.krupskiy.pages.ConstructorPage;
import ru.krupskiy.pages.LoginPage;
import ru.krupskiy.pages.MainPage;
import ru.krupskiy.pages.ProfilePage;

import static com.codeborne.selenide.Selenide.*;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertTrue;

public class ProfileTest {

    @After
    public void tearDown() {
        close();
    };

    @Test
    @DisplayName("Успешный переход по клику на 'Личный кабинет'. Google_chrome")
    public void enterProfilePage() {
        
        UserClient userClient;
        userClient = new UserClient();
        User user = User.getFixed();
        String accessToken = userClient.createUser(user)
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("accessToken");

        String email = user.getEmail();
        String password = user.getPassword();
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

        
        userClient.deleteUser(accessToken)
                .statusCode(SC_ACCEPTED);
    }

    @Test
    @DisplayName("Успешный переход по клику на 'Конструктор' из личного кабинета. Google_chrome")
    public void enterConstructorViaProfilePage() {
        
        UserClient userClient;
        userClient = new UserClient();
        User user = User.getFixed();
        String accessToken = userClient.createUser(user)
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("accessToken");

        String email = user.getEmail();
        String password = user.getPassword();
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

        
        userClient.deleteUser(accessToken)
                .statusCode(SC_ACCEPTED);
    }

    @Test
    @DisplayName("Успешный переход на главную по клику на логотип Stellar Burgers из личного кабинета. Google_chrome")
    public void clickOnLogoViaProfilePage() {
        
        UserClient userClient;
        userClient = new UserClient();
        User user = User.getFixed();
        String accessToken = userClient.createUser(user)
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("accessToken");

        String email = user.getEmail();
        String password = user.getPassword();
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

        
        userClient.deleteUser(accessToken)
                .statusCode(SC_ACCEPTED);
    }

    @Test
    @DisplayName("Успешный выход из аккаунта. Google_chrome")
    public void exitProfile() {
        
        UserClient userClient;
        userClient = new UserClient();
        User user = User.getFixed();
        String accessToken = userClient.createUser(user)
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("accessToken");

        String email = user.getEmail();
        String password = user.getPassword();
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

        
        userClient.deleteUser(accessToken)
                .statusCode(SC_ACCEPTED);
    }
}
