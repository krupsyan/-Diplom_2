package ru.krupskiy.test;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
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
import static ru.krupskiy.pages.MainPage.MAIN_URL;

public class ProfileTest {

    UserClient userClient;
    User user;
    String accessToken;
    String email;
    String password;
    MainPage mainPage;
    LoginPage loginPage;
    ProfilePage profilePage;
    @Before
    public void setup() {

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
        mainPage.clickLoginButton();
        loginPage = page(LoginPage.class);
        loginPage.authorize(email, password);
        mainPage.clickProfileButton();

        profilePage = page(ProfilePage.class);
    }

    @After
    public void tearDown() {
        close();

        userClient.deleteUser(accessToken)
                .statusCode(SC_ACCEPTED);
    };

    @Test
    @DisplayName("Успешный переход по клику на 'Личный кабинет'. Google_chrome")
    public void enterProfilePage() {
        profilePage.clickProfileButton();

        assertTrue(profilePage.isProfileTextDisplayed());
    }

    @Test
    @DisplayName("Успешный переход по клику на 'Конструктор' из личного кабинета. Google_chrome")
    public void enterConstructorViaProfilePage() {
        profilePage.clickConstructorButton();
        ConstructorPage constructorPage = page(ConstructorPage.class);

        assertTrue(constructorPage.isCollectBurgerTextDisplayed());
    }

    @Test
    @DisplayName("Успешный переход на главную по клику на логотип Stellar Burgers из личного кабинета. Google_chrome")
    public void clickOnLogoViaProfilePage() {
        profilePage.clickOnLogoButton();

        assertTrue(mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Успешный выход из аккаунта. Google_chrome")
    public void exitProfile() {
        profilePage.clickOnExitButton();
        loginPage.clickLoginButton();

        assertTrue(loginPage.isLoginButtonDisplayed());
    }
}
