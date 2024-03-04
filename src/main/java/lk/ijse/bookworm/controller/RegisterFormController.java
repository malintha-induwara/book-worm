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

public class RegisterFormController {

    @FXML
    private AnchorPane registerPane;

    @FXML
    private MFXPasswordField txtConfirmPassword;

    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtUsername;

    @FXML
    void btnLogin(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginForm.fxml"));
        Pane loginPane = (Pane) fxmlLoader.load();
        registerPane.getChildren().clear();
        registerPane.getChildren().add(loginPane);
    }

    @FXML
    void btnRegister(ActionEvent event) {

    }

}

