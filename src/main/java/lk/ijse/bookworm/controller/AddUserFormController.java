package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.dto.UserDto;

public class AddUserFormController {

    @FXML
    private MFXTextField txtAddress;

    @FXML
    private MFXTextField txtEmail;

    @FXML
    private MFXTextField txtName;

    @FXML
    private MFXPasswordField txtPassword;


    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);


    @FXML
    void btnAdd(ActionEvent event) {

        boolean isValidated = validateFields();

        if (!isValidated){
            return;
        }

        UserDto userDto = new UserDto(txtEmail.getText(),txtName.getText(),txtAddress.getText(),txtPassword.getText());
        boolean isSaved = userBO.saveUser(userDto);

        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"User Saved").show();
            clearFields();
        }
        else {
            new Alert(Alert.AlertType.ERROR, "User Doesnt Saved").show();
        }
    }

    private void clearFields() {
        txtEmail.clear();
        txtName.clear();
        txtAddress.clear();
        txtPassword.clear();
    }


    private boolean validateFields() {

        boolean isEmailValid = txtEmail.getText().matches("^[a-zA-Z]{3,}$");
        if (!isEmailValid) {
            txtEmail.requestFocus();
            txtEmail.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtEmail.getStyleClass().remove("mfx-text-field-error");

        boolean isNameValid = txtName.getText().matches("^[a-zA-Z]{3,}$");
        if (!isNameValid) {
            txtName.requestFocus();
            txtName.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtName.getStyleClass().remove("mfx-text-field-error");



        boolean isAddressValid = txtAddress.getText().matches("^[a-zA-Z]{3,}$");
        if (!isAddressValid) {
            txtAddress.requestFocus();
            txtAddress.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtAddress.getStyleClass().remove("mfx-text-field-error");


        boolean isPasswordValid = txtPassword.getText().matches("^[a-zA-Z]{3,}$");
        if (!isPasswordValid) {
            txtPassword.requestFocus();
            txtPassword.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtPassword.getStyleClass().remove("mfx-text-field-error");
        return true;
    }

    @FXML
    void btnCancel(ActionEvent event) {

    }

}

