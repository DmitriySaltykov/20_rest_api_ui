package api_ui_test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {


    public static void openUserProfileWithCookies(String userId, String token, String expires) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", userId));
        getWebDriver().manage().addCookie(new Cookie("token", token));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));
    }

    @Step("Проверка отсутствия в списке профиля удаленной книги")
    public static void checkDisappearBook(String bookId) {
        open("/profile");
        $("[id='" + bookId + "']").shouldBe(disappear);
    }
}
