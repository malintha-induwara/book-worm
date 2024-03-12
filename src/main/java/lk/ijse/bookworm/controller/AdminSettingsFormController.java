package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class AdminSettingsFormController {
    

    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtUsername;


    @FXML
    private Circle circleImg;


    public void initialize(){
        loadDetails();
    }

    private void loadDetails() {






    }


    @FXML
    void btnLoginOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }


}

