package ru.krupskiy.test;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.krupskiy.api.User;
import ru.krupskiy.api.UserClient;
import ru.krupskiy.pages.ConstructorPage;
import ru.krupskiy.pages.LoginPage;
import ru.krupskiy.pages.MainPage;
import ru.krupskiy.pages.ProfilePage;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertTrue;
import static ru.krupskiy.pages.MainPage.MAIN_URL;

public class ConstructorTest {

    @Test
    @DisplayName("Успешный переход между вкладками раздела 'Конструктор'. Google_chrome")
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

        MainPage mainPage = Selenide.open(MAIN_URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.authorize(email, password);
        mainPage.clickProfileButton();

        ProfilePage profilePage = Selenide.page(ProfilePage.class);
        profilePage.clickConstructorButton();
        ConstructorPage constructorPage = Selenide.page(ConstructorPage.class);

        constructorPage.clickSauceTab();
        assertTrue(constructorPage.isSauceTabActive());

        constructorPage.clickIngredientTab();
        assertTrue(constructorPage.isIngredientTabActive());

        constructorPage.clickBunTab();
        assertTrue(constructorPage.isBunTabActive());

        userClient.deleteUser(accessToken)
                .statusCode(SC_ACCEPTED);
    }
}
