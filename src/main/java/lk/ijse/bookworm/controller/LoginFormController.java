package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {


    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtUsername;

    @FXML
    private AnchorPane loginPane;

    @FXML
    void btnLogin(ActionEvent event) throws IOException {

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardMainForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
        stage.show();

        //Close the Current Window
        Stage loginStage = (Stage) loginPane.getScene().getWindow();
        loginStage.close();

    }

    @FXML
    void btnRegister(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/registerForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        loginPane.getChildren().clear();
        loginPane.getChildren().add(registerPane);
    }


}

