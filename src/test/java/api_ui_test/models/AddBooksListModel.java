package api_ui_test.models;

import lombok.Data;

import java.util.List;

@Data
public class AddBooksListModel {
    String userId;
    List<IsbnModel> collectionOfIsbns;
}