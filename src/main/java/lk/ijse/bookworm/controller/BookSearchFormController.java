package lk.ijse.bookworm.controller;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.BookBO;
import lk.ijse.bookworm.tm.BookTm;

public class BookSearchFormController {



    @FXML
    private TableColumn<BookTm, String> colAuthor;

    @FXML
    private TableColumn<BookTm, String> colAvailability;

    @FXML
    private TableColumn<BookTm, String> colGenre;

    @FXML
    private TableColumn<BookTm, String> colId;

    @FXML
    private TableColumn<BookTm, String> colTitle;

    @FXML
    private TableView<BookTm> tblBook;


    BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);

    public void initialize(){
        setCellValueFactory();
        loadBookDetails();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }



    private void loadBookDetails() {
        tblBook.getItems().clear();
        bookBO.getAllBooks().forEach(bookDto -> {
            tblBook.getItems().add(new BookTm(
                    bookDto.getBookId(),
                    bookDto.getTitle(),
                    bookDto.getAuthor(),
                    bookDto.getGenre(),
                    bookDto.isAvailability()? "Yes" : "No"
            ));
        });

    }


}





