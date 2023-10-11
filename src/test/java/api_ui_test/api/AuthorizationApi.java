package api_ui_test.api;

import api_ui_test.models.CredentialsModel;
import api_ui_test.models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AuthorizationApi {

    public LoginResponseModel login(CredentialsModel credentials) {
        return given()
                .body(credentials)
                .contentType(JSON)
                .when()
                .post("/Account/v1/Login")
                .then()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
    }
}
