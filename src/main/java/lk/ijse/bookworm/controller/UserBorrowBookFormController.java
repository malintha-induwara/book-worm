package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.BookBO;
import lk.ijse.bookworm.bo.custom.BookTransactionBO;
import lk.ijse.bookworm.bo.custom.impl.UserBOImpl;
import lk.ijse.bookworm.dto.BorrowBookDto;
import lk.ijse.bookworm.tm.UserBorrowBookTm;

import java.util.List;

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
    private TableColumn<UserBorrowBookTm, String> colReturned;

    @FXML
    private MFXDatePicker dpReturnDate;

    @FXML
    private TableView<UserBorrowBookTm> tblBorrowBook;

    @FXML
    private MFXTextField txtSearch;

    BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);

    BookTransactionBO bookTransactionBO = (BookTransactionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK_TRANSACTION);


    public void initialize() {
        loadBookIds();
        setCellValueFactory();
        loadBorrowedBooks();
    }

    private void loadBookIds() {
        cmbBookID.getItems().clear();
        bookBO.getAllBooks().forEach(dto -> {
            boolean availability = dto.isAvailability();
            if (availability) {
                cmbBookID.getItems().add(dto.getBookId());
            }
        });
    }

    private void loadBorrowedBooks() {
        List<BorrowBookDto> allBorrowedBooks = bookTransactionBO.getAllBorrowedBooks();
        ObservableList<UserBorrowBookTm> observableList = FXCollections.observableArrayList();
        for (BorrowBookDto dto : allBorrowedBooks) {
            if (dto.getUserId().equals(UserBOImpl.loggedUser.getEmail())){
                observableList.add(new UserBorrowBookTm(
                        dto.getId(),
                        dto.getBookId(),
                        dto.getBorrowDate(),
                        dto.getReturnDate(),
                        dto.isReturned()?"Yes":"No"
                ));
            }
        }
        tblBorrowBook.setItems(observableList);
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colReturned.setCellValueFactory(new PropertyValueFactory<>("isReturned"));
    }


    @FXML
    void btnAddOnAction(ActionEvent event) {

    }



}

