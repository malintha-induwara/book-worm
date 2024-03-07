package lk.ijse.bookworm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bookworm.tm.BranchTm;

public class BranchFormController {

    @FXML
    private AnchorPane branchPane;

    @FXML
    private TableColumn<?, ?> colBranchAddress;

    @FXML
    private TableColumn<?, ?> colBranchName;

    @FXML
    private TableColumn<?, ?> colBranchID;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableColumn<?, ?> colBranchAdmin;

    @FXML
    private TableView<BranchTm> tblBranch;

    @FXML
    void btnAddOnAction(ActionEvent event) {




    }

}

