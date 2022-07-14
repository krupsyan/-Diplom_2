package ru.krupskiy.test;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.krupskiy.api.User;
import ru.krupskiy.api.UserClient;
import ru.krupskiy.pages.ForgetPasswordPage;
import ru.krupskiy.pages.LoginPage;
import ru.krupskiy.pages.MainPage;
import ru.krupskiy.pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertTrue;
import static ru.krupskiy.pages.MainPage.MAIN_URL;

public class LoginYandexBrowserTest {

    UserClient userClient;
    User user;
    String accessToken;
    String email;
    String password;
    MainPage mainPage;
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver","/home/akrupskiy/IdeaProjects/Diplom_3/yandexdriver-22.5.0.1879-linux/yandexdriver");
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);

        userClient = new UserClient();
        user = User.getFixed();
        accessToken = userClient.createUser(user)
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("accessToken");

        email = user.getEmail();
        password = user.getPassword();

        mainPage = open(MAIN_URL, MainPage.class);
    }

    @After
    public void tearDown() {
        close();

        userClient.deleteUser(accessToken)
                .statusCode(SC_ACCEPTED);
    };

    @Test
    @DisplayName("Успешная авторизация по кнопке 'Войти в аккаунт' на главной. Yandex_browser")
    public void loginViaLoginButton() {
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.authorize(email, password);

        assertTrue(mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Успешная авторизация через кнопку 'Личный кабинет'. Yandex_browser")
    public void loginViaProfile() {
        mainPage.clickProfileButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.authorize(email, password);

        assertTrue(mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Успешная авторизация через кнопку в форме регистрации. Yandex_browser")
    public void loginViaRegistrationForm() {
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();
        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.clickLoginLink();
        loginPage.authorize(email, password);

        assertTrue(mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Успешная авторизация через кнопку в форме восстановления пароля. Yandex_browser")
    public void loginViaForgetPassword() {
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickForgetPasswordLink();
        ForgetPasswordPage forgetPasswordPage = page(ForgetPasswordPage.class);
        forgetPasswordPage.clickLoginLink();
        loginPage.authorize(email, password);

        assertTrue(mainPage.isMakeOrderButtonDisplayed());
    }
}
