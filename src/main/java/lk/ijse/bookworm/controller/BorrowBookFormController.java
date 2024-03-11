package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.BookBO;
import lk.ijse.bookworm.bo.custom.BookTransactionBO;
import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.dto.BorrowBookDto;
import lk.ijse.bookworm.tm.BookTm;
import lk.ijse.bookworm.tm.BorrowBookTm;

import java.util.List;

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

    BookTransactionBO bookTransactionBO = (BookTransactionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK_TRANSACTION);


    public void initialize() {
        loadUserIds();
        loadBookIds();
        setCellValueFactory();
        loadBorrowedBooks();
    }

    private void loadBorrowedBooks() {
        List<BorrowBookDto> allBorrowedBooks = bookTransactionBO.getAllBorrowedBooks();


        ObservableList<BorrowBookTm> observableList = FXCollections.observableArrayList();

        for (BorrowBookDto dto : allBorrowedBooks) {
            observableList.add(new BorrowBookTm(
                    dto.getId(),
                    dto.getUserId(),
                    dto.getBookId(),
                    dto.getBorrowDate(),
                    dto.getReturnDate()
            ));
        }

        for (int i = 0; i < observableList.size(); i++) {
            final  int index = i;
            if (allBorrowedBooks.get(i).isReturned()){
                observableList.get(i).getIsReturned().setSelected(true);
            }
            observableList.get(i).getIsReturned().setOnAction(actionEvent -> {
                boolean isUpdated = bookTransactionBO.updateBorrowedBook(observableList.get(index).getId());
                if (isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
                }
                else {
                    new Alert(Alert.AlertType.ERROR,"Not Updated").show();
                }
            });
        }

        tblBorrowBook.setItems(observableList);
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colIsReturned.setCellValueFactory(new PropertyValueFactory<>("isReturned"));


        Callback<TableColumn<BorrowBookTm, String>, TableCell<BorrowBookTm, String>> colRemoveCellFactory
                = new Callback<TableColumn<BorrowBookTm, String>, TableCell<BorrowBookTm, String>>()
        {
            @Override
            public TableCell<BorrowBookTm,String> call(final TableColumn<BorrowBookTm, String> param)
            {
                final TableCell<BorrowBookTm, String> cell = new TableCell<BorrowBookTm, String>()
                {
                    final MFXButton btn = new MFXButton("Remove");

                    {
                        btn.setOnAction(event -> {
                            BorrowBookTm tm = getTableView().getItems().get(getIndex());
                            boolean isDeleted = bookTransactionBO.deleteBorrowedBook(tm.getId());
                            //Alert
                            if (isDeleted){
                                new Alert(Alert.AlertType.CONFIRMATION,"User Deleted").show();
                            }
                            else {
                                new Alert(Alert.AlertType.ERROR, "User Doesnt Deleted").show();
                            }

                            tblBorrowBook.getItems().remove(getIndex());
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

