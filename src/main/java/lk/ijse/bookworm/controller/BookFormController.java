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
import javafx.stage.Stage;
import javafx.util.Callback;
import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.BookBO;
import lk.ijse.bookworm.tm.BookTm;
import lk.ijse.bookworm.tm.BranchTm;

import java.io.IOException;

public class BookFormController {


    @FXML
    private TableColumn<BookTm, String> colAuthor;

    @FXML
    private TableColumn<BookTm, String> colAvailability;

    @FXML
    private TableColumn<BookTm, String> colGenre;

    @FXML
    private TableColumn<BookTm, String> colId;

    @FXML
    private TableColumn<BookTm, String> colRemove;

    @FXML
    private TableColumn<BookTm, String> colTitle;

    @FXML
    private TableColumn<BookTm, String> colUpdate;

    @FXML
    private TableView<BookTm> tblBook;


    BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);


    public void initialize(){
        setCellValueFactory();
        loadAllBooks();
    }

    private void setCellValueFactory() {

        colId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));


        Callback<TableColumn<BookTm, String>, TableCell<BookTm, String>> colRemoveCellFactory
                = new Callback<TableColumn<BookTm, String>, TableCell<BookTm, String>>()
        {
            @Override
            public TableCell<BookTm,String> call(final TableColumn<BookTm, String> param)
            {
                final TableCell<BookTm, String> cell = new TableCell<BookTm, String>()
                {
                    final MFXButton btn = new MFXButton("Remove");

                    {
                        btn.setOnAction(event -> {
                            BookTm tm = getTableView().getItems().get(getIndex());

                            boolean isDeleted = bookBO.deleteBook(tm.getBookID());

                            //Alert
                            if (isDeleted){
                                new Alert(Alert.AlertType.CONFIRMATION,"User Deleted").show();
                            }
                            else {
                                new Alert(Alert.AlertType.ERROR, "User Doesnt Deleted").show();
                            }

                            tblBook.getItems().remove(getIndex());
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



        Callback<TableColumn<BookTm, String>, TableCell<BookTm, String>> colUpdateCellFactory
                = new Callback<>() {
            @Override
            public TableCell<BookTm, String> call(final TableColumn<BookTm, String> param) {
                final TableCell<BookTm, String> cell = new TableCell<>() {
                    final MFXButton btn = new MFXButton("Update");

                    {
                        btn.setOnAction(event -> {
                            BookTm tm = getTableView().getItems().get(getIndex());
                            //Catch the exception
                            try {
                                updateOnAction(tm.getBookID());
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


    @FXML
    void btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/bookDataForm.fxml"));
        Parent rootNode = fxmlLoader.load();

        BookDataFormController bookDataFormController = fxmlLoader.getController();
        bookDataFormController.setBookFormController(this);

        bookDataFormController.setBtnAndLblName("Add");


        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Add Book");
        stage.show();
    }

    private void updateOnAction(String bookId) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/bookDataForm.fxml"));
        Parent rootNode = fxmlLoader.load();

        BookDataFormController bookDataFormController = fxmlLoader.getController();
        bookDataFormController.setBookFormController(this);

        //Set Button Name
        bookDataFormController.setBtnAndLblName("Update");
        bookDataFormController.loadBookData(bookId);

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Update User");
        stage.show();
    }

    public void loadAllBooks() {

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

