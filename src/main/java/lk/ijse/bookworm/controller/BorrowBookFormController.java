package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.BookBO;
import lk.ijse.bookworm.bo.custom.UserBO;

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
    private TableView<?> tblBorrowBook;


    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);


    public void initialize() {
        loadUserIds();
        loadBookIds();



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

    }



}

