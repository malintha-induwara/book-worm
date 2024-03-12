package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lk.ijse.bookworm.bo.custom.impl.AdminBOImpl;

import java.io.IOException;

public class AdminDashboardMainFormController {

    @FXML
    private AnchorPane holderPane;

    @FXML
    private MFXButton btnBook;

    @FXML
    private MFXButton btnBookBorrow;

    @FXML
    private MFXButton btnBranch;

    @FXML
    private MFXButton btnDashboard;

    @FXML
    private MFXButton btnSetting;

    @FXML
    private MFXButton btnUser;


    @FXML
    private Circle cirAdminImage;

    @FXML
    private Label idAdminName;


    public void initialize() throws IOException {
        setAdminNameAndImage();
        loadAdminDashBoardForm();
    }

    private void setAdminNameAndImage() {
        Platform.runLater(() -> {
            idAdminName.setText(AdminBOImpl.admin.getUsername());
            Image image = new Image(AdminBOImpl.admin.getImgUrl());
            cirAdminImage.setFill(new ImagePattern(image));

        });
    }

    private void loadAdminDashBoardForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminDashBoardForm.fxml"));
        Pane dashboardPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(dashboardPane);
    }

    @FXML
    void btnBookOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookForm.fxml"));
        Pane bookPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(bookPane);
    }

    @FXML
    void btnBorrowBookOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/borrowBookForm.fxml"));
        Pane borrowBookPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(borrowBookPane);
    }

    @FXML
    void btnBranchOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/branchForm.fxml"));
        Pane borrowBookPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(borrowBookPane);
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminDashBoardForm.fxml"));
        Pane dashboardPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(dashboardPane);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/mainForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login");
        stage.show();

        //Close the Current Window
        Stage dashboard= (Stage) holderPane.getScene().getWindow();
        dashboard.close();
    }


    @FXML
    void btnSettingsOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminSettingsForm.fxml"));
        Pane settingPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(settingPane);
    }


    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userForm.fxml"));
        Pane userPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(userPane);
    }


}

