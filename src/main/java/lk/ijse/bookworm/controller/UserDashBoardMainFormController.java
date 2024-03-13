package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDashBoardMainFormController {


    @FXML
    private MFXButton btnBookSearch;

    @FXML
    private MFXButton btnHistory;

    @FXML
    private MFXButton btnSetting;

    @FXML
    private AnchorPane holderPane;


    public void initialize() throws IOException {
        loadBookSearchForm();
    }

    private void loadBookSearchForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookSearchForm.fxml"));
        Pane bookSearchPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(bookSearchPane);
    }

    @FXML
    void btnBookSearchOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookSearchForm.fxml"));
        Pane bookSearchPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(bookSearchPane);
    }

    @FXML
    void btnHistoryOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userBorrowBookForm.fxml"));
        Pane historyPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(historyPane);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/mainForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login");
        stage.show();

        //Close the Current Window
        Stage dashboard= (Stage) holderPane.getScene().getWindow();
        dashboard.close();
    }

    @FXML
    void btnSettingsOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userSettingsForm.fxml"));
        Pane userSettingsPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(userSettingsPane);
    }

}

