package api_ui_test.tests;

import api_ui_test.models.AddBooksListModel;
import api_ui_test.models.DeleteBookModel;
import api_ui_test.models.IsbnModel;
import api_ui_test.models.LoginResponseModel;
import api_ui_test.pages.ProfilePage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static api_ui_test.tests.TestData.credentials;


public class DeleteBooksFromListTest extends TestBase {
    LoginResponseModel loginResponse;


    @Test
    @Step("Проверка отсутствия удаленного товара в профиле")
    void checkEmptyProfileTest() {
        loginResponse = authorizationApi.login(credentials);
        IsbnModel isbnModel = new IsbnModel();

        isbnModel.setIsbn("9781449325862");
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        AddBooksListModel booksList = new AddBooksListModel();
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);

        DeleteBookModel deleteBookModel = new DeleteBookModel();
        deleteBookModel.setIsbn("9781449325862");
        deleteBookModel.setUserId(loginResponse.getUserId());


        booksApi.addBook(loginResponse, booksList);
        booksApi.deleteOneBook(loginResponse, deleteBookModel);


        ProfilePage profilePage = new ProfilePage();
        profilePage
                .openProfilePage()
                .checkEmptyTable();
    }

    @AfterEach
    void cleanUp() {
        booksApi.deleteAllBooks(loginResponse);

    }


}
