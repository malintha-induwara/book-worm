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

public class LoginFormController {


    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtUsername;

    @FXML
    private AnchorPane loginPane;

    @FXML
    void btnLogin(ActionEvent event) {


    }

    @FXML
    void btnRegister(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/registerForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        loginPane.getChildren().clear();
        loginPane.getChildren().add(registerPane);
    }


}

