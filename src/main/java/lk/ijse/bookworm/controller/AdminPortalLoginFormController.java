package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AdminPortalLoginFormController {

    @FXML
    private AnchorPane adminLoginPane;

    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtUsername;

    @FXML
    void btnUserLogin(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginForm.fxml"));
        Pane userLoginPane = (Pane) fxmlLoader.load();
        adminLoginPane.getChildren().clear();
        adminLoginPane.getChildren().add(userLoginPane);
    }

    @FXML
    void btnLogin(ActionEvent event) {

    }

    @FXML
    void btnRegister(MouseEvent event) {

    }



}

