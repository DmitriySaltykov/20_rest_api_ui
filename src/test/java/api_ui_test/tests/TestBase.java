package api_ui_test.tests;

import api_ui_test.api.AuthorizationApi;
import api_ui_test.api.BooksApi;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;

import helpers.Attach;

import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;



public class TestBase {
    AuthorizationApi authorizationApi = new AuthorizationApi();
    BooksApi booksApi = new BooksApi();

    @BeforeAll
    static void beforeAll() {

        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        RestAssured.baseURI = System.getProperty("baseURI", "https://demoqa.com");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion", "100.0");
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = System.getProperty("selenoidBaseUrl", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;


    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
                @AfterEach
        void addAttachments() {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();

        }

}