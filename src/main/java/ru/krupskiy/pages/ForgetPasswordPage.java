package ru.krupskiy.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

public class ForgetPasswordPage {
    //локатор ссылки "Войти"
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement loginLink;

    @Step("нажатие на ссылку 'Войти'")
    public void clickLoginLink() {
        this.loginLink.click();
    }
}
