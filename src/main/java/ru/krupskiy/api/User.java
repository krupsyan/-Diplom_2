package ru.krupskiy.api;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String email;
    private String password;
    private String name;

    @Step("Создание пользователя с рандомным email")
    public static User getFixed() {
        String email = RandomStringUtils.randomAlphanumeric(10) + "@krupsyan" + ".ru";
        String password = "123456";
        String name = "Иван";

        return new User(email, password, name);
    }

}
