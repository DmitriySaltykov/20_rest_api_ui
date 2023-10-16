package api_ui_test.api;

import api_ui_test.models.CredentialsModel;
import api_ui_test.models.LoginResponseModel;
import io.qameta.allure.Step;

import static api_ui_test.specs.LoginSpecs.LoginRequestSpec;
import static api_ui_test.specs.LoginSpecs.successAuthSpec;
import static io.restassured.RestAssured.given;


public class AuthorizationApi {
    @Step("Авторизация пользователя")
    public LoginResponseModel login(CredentialsModel credentials) {
        return given(LoginRequestSpec)
                .body(credentials)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(successAuthSpec)
                .extract().as(LoginResponseModel.class);
    }
}
