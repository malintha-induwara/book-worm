package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class BorrowBookFormController {


    @FXML
    private AnchorPane borrowBookPane;

    @FXML
    private MFXComboBox<String> cmbBookID;

    @FXML
    private MFXComboBox<String> cmbUserID;

    @FXML
    private TableColumn<?, ?> colBookID;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colIsReturned;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colUserID;

    @FXML
    private TableView<?> tblBranch;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }



}

