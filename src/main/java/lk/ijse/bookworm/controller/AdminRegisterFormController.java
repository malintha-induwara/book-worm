package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.AdminBO;
import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.dto.AdminDto;

import java.io.IOException;

public class AdminRegisterFormController {

    @FXML
    private Label lblError;

    @FXML
    private AnchorPane registerPane;

    @FXML
    private MFXPasswordField txtConfirmPassword;

    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtUsername;

    private final AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);

    @FXML
    void btnLogin(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminLoginForm.fxml"));
        Pane loginPane = (Pane) fxmlLoader.load();
        registerPane.getChildren().clear();
        registerPane.getChildren().add(loginPane);

    }

    @FXML
    void btnRegister(ActionEvent event) throws IOException {

        boolean isLoginValidated = validateRegister();

        if (!isLoginValidated) {
            return;
        }

        AdminDto adminDto = new AdminDto(txtUsername.getText(), txtPassword.getText());
        boolean isSaved = adminBO.saveAdmin(adminDto);

        if (isSaved) {
            clearFields();
            loadLoginPane();
        } else {
            new Alert(Alert.AlertType.ERROR, "User Already Exists ").show();
        }
    }

    private void loadLoginPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userLoginForm.fxml"));
        Pane loginPane = (Pane) fxmlLoader.load();
        registerPane.getChildren().clear();
        registerPane.getChildren().add(loginPane);
    }

    private void clearFields() {
        txtUsername.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
    }

    private boolean validateRegister() {

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

