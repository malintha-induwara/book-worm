package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.BookBO;
import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.tm.BorrowBookTm;

public class BorrowBookFormController {


    @FXML
    private AnchorPane borrowBookPane;

    @FXML
    private MFXComboBox<String> cmbBookID;

    @FXML
    private MFXComboBox<String> cmbUserID;

    @FXML
    private TableColumn<BorrowBookTm, String> colBookID;

    @FXML
    private TableColumn<BorrowBookTm, String> colBorrowDate;

    @FXML
    private TableColumn<BorrowBookTm, String> colID;

    @FXML
    private TableColumn<BorrowBookTm, String> colIsReturned;

    @FXML
    private TableColumn<BorrowBookTm, String> colRemove;

    @FXML
    private TableColumn<BorrowBookTm, String> colReturnDate;

    @FXML
    private TableColumn<BorrowBookTm, String> colUserID;

    @FXML
    private MFXDatePicker dpReturnDate;


    @FXML
    private TableView<BorrowBookTm> tblBorrowBook;


    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);


    public void initialize() {
        loadUserIds();
        loadBookIds();
        setCellValueFactory();

    }

    private void setCellValueFactory() {

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));








    }

    private void loadBookIds() {
        bookBO.getAllBooks().forEach(dto -> {
            cmbBookID.getItems().add(dto.getBookId());
        });

    }

    private void loadUserIds() {
        userBO.getAllUsers().forEach(dto -> {
            cmbUserID.getItems().add(dto.getEmail());
        });
    }


    @FXML
    void btnAddOnAction(ActionEvent event) {

        boolean isValidated = validateFields();

        if (!isValidated) {
            return;
        }







    }

    private boolean validateFields() {

        boolean isUserIdEmpty = cmbUserID.getText().isEmpty();

        if (isUserIdEmpty) {
            cmbUserID.requestFocus();
            cmbUserID.getStyleClass().add("mfx-combo-box-error");
            return false;
        }

        cmbUserID.getStyleClass().remove("mfx-combo-box-error");


        boolean isBookIdEmpty = cmbBookID.getText().isEmpty();
        if (isBookIdEmpty) {
            cmbBookID.requestFocus();
            cmbBookID.getStyleClass().add("mfx-combo-box-error");
            return false;
        }
        cmbBookID.getStyleClass().remove("mfx-combo-box-error");

        return true;
    }


}

