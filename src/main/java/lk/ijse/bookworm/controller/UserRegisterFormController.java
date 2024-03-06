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

public class UserRegisterFormController {

    @FXML
    private AnchorPane registerPane;

    @FXML
    private MFXPasswordField txtConfirmPassword;

    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtUsername;

    @FXML
    private MFXTextField txtName;


    @FXML
    private MFXTextField txtAddress;

    @FXML
    private Label lblError;

    @FXML
    void btnLogin(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userLoginForm.fxml"));
        Pane loginPane = (Pane) fxmlLoader.load();
        registerPane.getChildren().clear();
        registerPane.getChildren().add(loginPane);
    }

    @FXML
    void btnRegister(ActionEvent event) {

        boolean isLoginValidated = validateRegister();

        if (!isLoginValidated) {
            return;
        }


    }

    private boolean validateRegister() {

        boolean isNameValid = txtName.getText().matches("^[a-zA-Z]{3,}$");
        if (!isNameValid) {
            txtName.requestFocus();
            txtName.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtName.getStyleClass().remove("mfx-text-field-error");


        boolean isAddressValid = txtAddress.getText().matches("^[a-zA-Z0-9]{3,}$");

        if (!isAddressValid) {
            txtAddress.requestFocus();
            txtAddress.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtAddress.getStyleClass().remove("mfx-text-field-error");


        boolean isUserNameValid = txtUsername.getText().matches("^[a-zA-Z0-9]{3,}$");
        if (!isUserNameValid) {
            txtUsername.requestFocus();
            txtUsername.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtUsername.getStyleClass().remove("mfx-text-field-error");

        boolean isPasswordValid = txtPassword.getText().matches("^[a-zA-Z0-9]{3,}$");

        if (!isPasswordValid) {
            txtPassword.requestFocus();
            txtPassword.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtPassword.getStyleClass().remove("mfx-text-field-error");

        boolean isConfirmPasswordValid = txtConfirmPassword.getText().matches("^[a-zA-Z0-9]{3,}$");

        if (!isConfirmPasswordValid) {
            txtConfirmPassword.requestFocus();
            txtConfirmPassword.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtConfirmPassword.getStyleClass().remove("mfx-text-field-error");


        if (!txtPassword.getText().equals(txtConfirmPassword.getText())) {
            lblError.setVisible(true);
            txtPassword.getStyleClass().add("mfx-text-field-error");
            txtConfirmPassword.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        lblError.setVisible(false);
        txtPassword.getStyleClass().remove("mfx-text-field-error");
        txtConfirmPassword.getStyleClass().remove("mfx-text-field-error");

        return true;
    }

}

