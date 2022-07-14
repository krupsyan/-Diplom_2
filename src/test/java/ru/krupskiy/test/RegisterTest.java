package ru.krupskiy.test;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
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
    LoginPage loginPage;
    MainPage mainPage;
    RegistrationPage registrationPage;
    String accessToken;

    @Before
    public void setup() {
        userClient = new UserClient();
        user = User.getFixed();

        mainPage = open(MAIN_URL, MainPage.class);
        mainPage.clickLoginButton();
        loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();
        registrationPage = page(RegistrationPage.class);
        registrationPage.setName(user.getName());
        registrationPage.setEmail(user.getEmail());
    }

    @After
    public void tearDown() {
        close();
    };

    @Test
    @DisplayName("Успешная регистрация. Google_chrome")
    public void positiveScenarioRegistration() {
        registrationPage.setPassword(user.getPassword());
        registrationPage.clickRegisterButton();

        assertTrue(loginPage.isLoginFormDisplayed());

        UserCredentials creds = UserCredentials.from(user);
        accessToken = userClient.login(creds)
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
        user.setPassword("12345");
        registrationPage.setPassword(user.getPassword());
        registrationPage.clickRegisterButton();

        if(!registrationPage.isIncorrectPasswordMessageDisplayed()){
            UserCredentials creds = UserCredentials.from(user);
            accessToken = userClient.login(creds)
                    .assertThat()
                    .statusCode(SC_OK)
                    .extract()
                    .path("accessToken");

            userClient.deleteUser(accessToken)
                    .statusCode(SC_ACCEPTED);
        }

        assertTrue(registrationPage.isIncorrectPasswordMessageDisplayed());
    }
}
