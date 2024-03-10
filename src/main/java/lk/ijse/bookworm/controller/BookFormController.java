package lk.ijse.bookworm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookFormController {




    @FXML
    void btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/bookDataForm.fxml"));
        Parent rootNode = fxmlLoader.load();

        BookDataFormController bookDataFormController = fxmlLoader.getController();
        bookDataFormController.setBookFormController(this);

        bookDataFormController.setBtnAndLblName("Add");


        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Add Book");
        stage.show();
    }

    private void updateOnAction(String bookId) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/bookDataForm.fxml"));
        Parent rootNode = fxmlLoader.load();

        BookDataFormController bookDataFormController = fxmlLoader.getController();
        bookDataFormController.setBookFormController(this);

        //Set Button Name
        bookDataFormController.setBtnAndLblName("Add");
        bookDataFormController.loadBookData(bookId);

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Update User");
        stage.show();
    }

}

