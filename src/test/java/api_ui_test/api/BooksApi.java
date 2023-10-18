package api_ui_test.api;

import api_ui_test.models.AddBooksListModel;
import api_ui_test.models.DeleteBookModel;
import api_ui_test.models.LoginResponseModel;
import io.qameta.allure.Step;

import static api_ui_test.specs.BooksSpec.*;
import static io.restassured.RestAssured.given;


public class BooksApi {

    @Step("Добавление книг")
    public void addBook(LoginResponseModel loginResponse, AddBooksListModel booksList) {
        given(baseRequestSpec)

                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(booksList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(addBook201ResponseSpec);
    }

    @Step("Удаление одной книги из профиля")
    public void deleteOneBook(LoginResponseModel loginResponse, DeleteBookModel deleteBookModel) {
        given(baseRequestSpec)

                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .body(deleteBookModel)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(deleteBook204ResponseSpec);


    }


}
