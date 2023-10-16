package api_ui_test.pages;

import api_ui_test.models.LoginResponseModel;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {


    @Step("Добавление кук")
    public ProfilePage addCookies(LoginResponseModel response) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires()));

        return this;
    }

    @Step("Открытие страницы профиля")
    public ProfilePage openProfilePage() {
        open("/profile");

        return this;
    }

    @Step("Проверка отсутствия книг в профиле")
    public void checkEmptyTable() {
        $("[id='see-book-Git Pocket Guide']").shouldNotBe(visible);
    }
}
