package ru.krupskiy.test;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;

import ru.krupskiy.api.User;
import ru.krupskiy.api.UserClient;
import ru.krupskiy.api.UserCredentials;
import ru.krupskiy.pages.LoginPage;
import ru.krupskiy.pages.MainPage;
import ru.krupskiy.pages.RegistrationPage;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertTrue;
import static ru.krupskiy.pages.MainPage.MAIN_URL;

public class RegisterTest {
    UserClient userClient;
    User user;

    @Test
    @DisplayName("Успешная регистрация. Google_chrome")
    public void positiveScenarioRegistration() {
        userClient = new UserClient();
        user = User.getFixed();

        MainPage mainPage = open(MAIN_URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();
        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.setName(user.getName());
        registrationPage.setEmail(user.getEmail());
        registrationPage.setPassword(user.getPassword());
        registrationPage.clickRegisterButton();

        assertTrue(loginPage.isLoginFormDisplayed());

        UserCredentials creds = UserCredentials.from(user);
        String accessToken = userClient.login(creds)
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("accessToken");

        userClient.deleteUser(accessToken)
                .statusCode(SC_ACCEPTED);
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля. Минимальный пароль — шесть символов. Google_chrome")
    public void tooShortPasswordRegistration() {
        String email = RandomStringUtils.randomAlphanumeric(6) + "@" + RandomStringUtils.randomAlphanumeric(6) + ".ru";
        String password = "12345";
        String name = "Иван";

        MainPage mainPage = open(MAIN_URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();
        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.setName(name);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        registrationPage.clickRegisterButton();

        assertTrue(registrationPage.isIncorrectPasswordMessageDisplayed());
    }
}
