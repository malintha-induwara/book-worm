package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.BranchBO;
import lk.ijse.bookworm.tm.BranchTm;
import lk.ijse.bookworm.tm.UserTm;

import java.io.IOException;

public class BranchFormController {

    @FXML
    private AnchorPane branchPane;

    @FXML
    private TableColumn<BranchTm, String> colBranchAddress;

    @FXML
    private TableColumn<BranchTm, String> colBranchName;

    @FXML
    private TableColumn<BranchTm, String> colBranchID;

    @FXML
    private TableColumn<BranchTm, String> colRemove;

    @FXML
    private TableColumn<BranchTm, String> colUpdate;

    @FXML
    private TableColumn<BranchTm, String> colBranchAdmin;

    @FXML
    private TableView<BranchTm> tblBranch;

    BranchBO branchBO =(BranchBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRANCH);

    public void initialize(){
        setCellValueFactory();
        loadAllBranches();
    }

    private void setCellValueFactory() {
        colBranchID.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        colBranchName.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colBranchAddress.setCellValueFactory(new PropertyValueFactory<>("branchAddress"));
        colBranchAdmin.setCellValueFactory(new PropertyValueFactory<>("adminName"));



        Callback<TableColumn<BranchTm, String>, TableCell<BranchTm, String>> colRemoveCellFactory
                = new Callback<TableColumn<BranchTm, String>, TableCell<BranchTm, String>>()
        {
            @Override
            public TableCell<BranchTm,String> call(final TableColumn<BranchTm, String> param)
            {
                final TableCell<BranchTm, String> cell = new TableCell<BranchTm, String>()
                {
                    final MFXButton btn = new MFXButton("Remove");

                    {
                        btn.setOnAction(event -> {
                            BranchTm tm = getTableView().getItems().get(getIndex());
                            boolean isDeleted = branchBO.deleteBranch(tm.getBranchID());

                            //Alert
                            if (isDeleted){
                                new Alert(Alert.AlertType.CONFIRMATION,"User Deleted").show();
                            }
                            else {
                                new Alert(Alert.AlertType.ERROR, "User Doesnt Deleted").show();
                            }


                            tblBranch.getItems().remove(getIndex());
                        });

                        btn.getStyleClass().add("mfx-button-remove");
                    }

                    @Override
                    public void updateItem(String item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        }
                        else {
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        colRemove.setCellFactory(colRemoveCellFactory);



        Callback<TableColumn<BranchTm, String>, TableCell<BranchTm, String>> colUpdateCellFactory
                = new Callback<>() {
            @Override
            public TableCell<BranchTm, String> call(final TableColumn<BranchTm, String> param) {
                final TableCell<BranchTm, String> cell = new TableCell<>() {
                    final MFXButton btn = new MFXButton("Update");

                    {
                        btn.setOnAction(event -> {
                            BranchTm tm = getTableView().getItems().get(getIndex());

                            //Catch the exception
                            try {
                                updateOnAction(tm.getBranchID());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        });

                        btn.getStyleClass().add("mfx-button-update");
                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        colUpdate.setCellFactory(colUpdateCellFactory);


    }


    public void loadAllBranches() {


    }


    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/branchDataForm.fxml"));
        Parent rootNode = loader.load();


        BranchDataFormController branchDataFormController = loader.getController();
        branchDataFormController.setBranchFormController(this);


        //Set Button Name
        branchDataFormController.setBtnAndLblName("Add");


        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Add User");
        stage.show();


    }


    private void updateOnAction(String branchId) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/userDataForm.fxml"));
        Parent rootNode = loader.load();

        BranchDataFormController branchDataFormController = loader.getController();
        branchDataFormController.setBranchFormController(this);

        //Set Button Name
        branchDataFormController.setBtnAndLblName("Update");
        branchDataFormController.loadBranchData(branchId);

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Update User");
        stage.show();
    }

}

