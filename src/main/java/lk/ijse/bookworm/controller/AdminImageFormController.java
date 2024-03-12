package lk.ijse.bookworm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class AdminImageFormController {


    @FXML
    private Circle circleImg;

    @FXML
    private Label lblError;

    @FXML
    private AnchorPane registerPane;


    public void initialize() {
        loadDefaultImage();


    }

    private void loadDefaultImage() {
        Image image = new Image("/assets/images/addUserImage.png");
        circleImg.setFill(new ImagePattern(image));
    }


    @FXML
    void btnLogin(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminLoginForm.fxml"));
        Pane loginPane = (Pane) fxmlLoader.load();
        registerPane.getChildren().clear();
        registerPane.getChildren().add(loginPane);
    }

    @FXML
    void btnNextOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminRegisterForm.fxml"));
        Pane loginPane = (Pane) fxmlLoader.load();
        registerPane.getChildren().clear();
        registerPane.getChildren().add(loginPane);
    }

    @FXML
    void circleImgOnAction(MouseEvent event) {

    }









}

