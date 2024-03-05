package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.regex.Pattern;

public class UserLoginFormController {


    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtUsername;

    @FXML
    private AnchorPane loginPane;


    @FXML
    private Label lblError;


    @FXML
    void btnLogin(ActionEvent event) throws IOException {

        boolean isLoginValidated = validateLogin();

        if (!isLoginValidated) {
            return;
        }


    }

    private boolean validateLogin() {

        boolean isUserNameValid = Pattern.matches("^[a-zA-Z0-9]{3,}$", txtUsername.getText());
        if (!isUserNameValid) {
            txtUsername.requestFocus();
            txtUsername.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtUsername.getStyleClass().remove("mfx-text-field-error");


        boolean isPasswordValid = Pattern.matches("^[a-zA-Z0-9]{3,}$", txtPassword.getText());
        if (!isPasswordValid) {
            txtPassword.requestFocus();
            txtPassword.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtPassword.getStyleClass().remove("mfx-text-field-error");

        return true;
    }

    @FXML
    void btnRegister(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userRegisterForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        loginPane.getChildren().clear();
        loginPane.getChildren().add(registerPane);
    }


    @FXML
    void btnAdminLogin(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminLoginForm.fxml"));
        Pane adminLoginPane = (Pane) fxmlLoader.load();
        loginPane.getChildren().clear();
        loginPane.getChildren().add(adminLoginPane);
    }


}

