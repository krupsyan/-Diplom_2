package ru.krupskiy.test;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.krupskiy.api.User;
import ru.krupskiy.api.UserClient;
import ru.krupskiy.pages.ForgetPasswordPage;
import ru.krupskiy.pages.LoginPage;
import ru.krupskiy.pages.MainPage;
import ru.krupskiy.pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    @After
    public void tearDown() {
        close();
    };

    @Test
    @DisplayName("Успешная авторизация по кнопке 'Войти в аккаунт' на главной")
    public void loginViaLoginButton() {
        
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

        assertTrue(mainPage.isMakeOrderButtonDisplayed());

        
        userClient.deleteUser(accessToken)
                .statusCode(SC_ACCEPTED);
    }

    @Test
    @DisplayName("Успешная авторизация через кнопку 'Личный кабинет'. Google_chrome")
    public void loginViaProfile() {
        
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
        mainPage.clickProfileButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        assertTrue(mainPage.isMakeOrderButtonDisplayed());

        
        userClient.deleteUser(accessToken)
                .statusCode(SC_ACCEPTED);
    }

    @Test
    @DisplayName("Успешная авторизация через кнопку в форме регистрации. Google_chrome")
    public void loginViaRegistrationForm() {
        
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
        loginPage.clickRegisterLink();
        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.clickLoginLink();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        assertTrue(mainPage.isMakeOrderButtonDisplayed());

        
        userClient.deleteUser(accessToken)
        .statusCode(SC_ACCEPTED);
        }

    @Test
    @DisplayName("Успешная авторизация через кнопку в форме восстановления пароля. Google_chrome")
    public void loginViaForgetPassword() {
        
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
        loginPage.clickForgetPasswordLink();
        ForgetPasswordPage forgetPasswordPage = page(ForgetPasswordPage.class);
        forgetPasswordPage.clickLoginLink();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        assertTrue(mainPage.isMakeOrderButtonDisplayed());

        
        userClient.deleteUser(accessToken)
                .statusCode(SC_ACCEPTED);
    }
}
