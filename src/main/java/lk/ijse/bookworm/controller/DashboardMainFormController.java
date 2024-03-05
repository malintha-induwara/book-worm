package lk.ijse.bookworm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardMainFormController {

    @FXML
    private AnchorPane holderPane;


    public void initialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashBoardForm.fxml"));
        Pane dashboardPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(dashboardPane);
    }



    @FXML
    void btnBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnBorrowBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnBranchOnAction(ActionEvent event) {

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

    }


    @FXML
    void btnSettingsOnAction(ActionEvent event) {

    }


    @FXML
    void btnUserOnAction(ActionEvent event) {

    }


}

