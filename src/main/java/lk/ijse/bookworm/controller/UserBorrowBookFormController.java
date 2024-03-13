package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bookworm.tm.UserBorrowBookTm;

public class UserBorrowBookFormController {



    @FXML
    private MFXFilterComboBox<String> cmbBookID;

    @FXML
    private TableColumn<UserBorrowBookTm, String> colBookID;

    @FXML
    private TableColumn<UserBorrowBookTm, String> colBorrowDate;

    @FXML
    private TableColumn<UserBorrowBookTm, String> colID;

    @FXML
    private TableColumn<UserBorrowBookTm, String> colReturnDate;

    @FXML
    private MFXDatePicker dpReturnDate;

    @FXML
    private TableView<UserBorrowBookTm> tblBorrowBook;

    @FXML
    private MFXTextField txtSearch;


    public void initialize() {
        setCellValueFactory();

    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
    }


    @FXML
    void btnAddOnAction(ActionEvent event) {

    }



}

