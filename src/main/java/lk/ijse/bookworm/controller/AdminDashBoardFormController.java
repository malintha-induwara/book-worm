package lk.ijse.bookworm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.BookTransactionBO;
import lk.ijse.bookworm.dto.BorrowBookDto;
import lk.ijse.bookworm.tm.LateBookDetailTm;

import java.time.LocalDate;
import java.util.List;

public class AdminDashBoardFormController {


    @FXML
    private AnchorPane dashBoardPane;


    @FXML
    private TableColumn<LateBookDetailTm, String> colBookID;

    @FXML
    private TableColumn<LateBookDetailTm, String> colBorrowDate;

    @FXML
    private TableColumn<LateBookDetailTm, String> colID;

    @FXML
    private TableColumn<LateBookDetailTm, String> colLateCount;

    @FXML
    private TableColumn<LateBookDetailTm, String> colReturnDate;

    @FXML
    private TableColumn<LateBookDetailTm, String> colUserID;

    @FXML
    private TableView<LateBookDetailTm> tblBorrowBook;

    @FXML
    private Label lblBookCount;

    @FXML
    private Label lblDueBookCount;

    @FXML
    private Label lblUserCount;


    BookTransactionBO bookTransactionBO = (BookTransactionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK_TRANSACTION);


    public void initialize(){
        setCellValueFactory();
        loadLateBookDetails();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colLateCount.setCellValueFactory(new PropertyValueFactory<>("lateCount"));
    }

    private void loadLateBookDetails() {

        List<BorrowBookDto> allLateBookDetails = bookTransactionBO.getAllLateBookDetails();

        for (BorrowBookDto borrowBookDto : allLateBookDetails) {
            tblBorrowBook.getItems().add(new LateBookDetailTm(
                    borrowBookDto.getId(),
                    borrowBookDto.getUserId(),
                    borrowBookDto.getBookId(),
                    borrowBookDto.getBorrowDate(),
                    borrowBookDto.getReturnDate(),
                    Integer.toString(
                            LocalDate.parse(borrowBookDto.getReturnDate()).compareTo(LocalDate.parse(borrowBookDto.getBorrowDate())
                            ))
            ));
        }
    }



}

