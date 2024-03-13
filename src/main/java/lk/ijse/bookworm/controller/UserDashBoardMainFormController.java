package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lk.ijse.bookworm.bo.custom.impl.UserBOImpl;

import java.io.IOException;

public class UserDashBoardMainFormController {


    @FXML
    private MFXButton btnBookSearch;

    @FXML
    private MFXButton btnBorrowBook;

    @FXML
    private MFXButton btnSetting;

    @FXML
    private AnchorPane holderPane;

    @FXML
    private Label idUserName;

    @FXML
    private Circle cirUserImage;


    @FXML
    private Pane imgAndNameHolderPane;

    public void initialize() throws IOException {
        setUserNameAndImage(true);
        loadBookSearchForm();
    }

    private void setUserNameAndImage(boolean flag) {
        imgAndNameHolderPane.setVisible(flag);
        Platform.runLater(() -> {
            idUserName.setText(UserBOImpl.loggedUser.getName());
            Image image = new Image(UserBOImpl.loggedUser.getImgUrl());
            cirUserImage.setFill(new ImagePattern(image));
        });

    }

    private void loadBookSearchForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookSearchForm.fxml"));
        Pane bookSearchPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(bookSearchPane);
    }

    @FXML
    void btnBookSearchOnAction(ActionEvent event) throws IOException {
        setUserNameAndImage(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookSearchForm.fxml"));
        Pane bookSearchPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(bookSearchPane);
    }

    @FXML
    void btnBorrowBookOnAction(ActionEvent event) throws IOException {
        setUserNameAndImage(true);
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
        setUserNameAndImage(false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userSettingsForm.fxml"));
        Pane userSettingsPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(userSettingsPane);
    }

}

