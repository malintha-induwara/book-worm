package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.BranchBO;
import lk.ijse.bookworm.dto.BranchDto;

import java.util.List;

public class BookDataFormController {


    @FXML
    private MFXButton btnAction;

    @FXML
    private MFXComboBox<String> colBranch;

    @FXML
    private MFXTextField txtAuthor;

    @FXML
    private MFXTextField txtBookId;

    @FXML
    private MFXTextField txtGenre;

    @FXML
    private MFXTextField txtTitle;

    @FXML
    private Label lblAction;

    private BookFormController bookFormController;



    BranchBO branchBO = (BranchBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRANCH);


    public void initialize(){
        loadBranches();
    }

    private void loadBranches() {
        List<BranchDto> branchDtoList = branchBO.getAllBranch();
        for (BranchDto branchDto : branchDtoList) {
            colBranch.getItems().add(branchDto.getBranchID());
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        closeTheWindow();
    }

    private void closeTheWindow() {
        Stage userDataStage= (Stage) txtBookId.getScene().getWindow();
        userDataStage.close();
    }

    @FXML
    void btnAction(ActionEvent event) {
        boolean isValidated = validateFields();

        if (!isValidated){
            return;
        }






    }

    private boolean validateFields() {

        boolean isBookIdValid = txtBookId.getText().matches("[a-zA-Z]{3,}$");

        if (!isBookIdValid) {
            txtBookId.requestFocus();
            txtBookId.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtBookId.getStyleClass().remove("mfx-text-field-error");


        boolean isTitleValid = txtTitle.getText().matches("[a-zA-Z]{3,}$");

        if (!isTitleValid) {
            txtTitle.requestFocus();
            txtTitle.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtTitle.getStyleClass().remove("mfx-text-field-error");


        boolean isAuthorValid = txtAuthor.getText().matches("[a-zA-Z]{3,}$");

        if (!isAuthorValid) {
            txtAuthor.requestFocus();
            txtAuthor.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtAuthor.getStyleClass().remove("mfx-text-field-error");


        boolean isGenreValid = txtGenre.getText().matches("[a-zA-Z]{3,}$");

        if (!isGenreValid) {
            txtGenre.requestFocus();
            txtGenre.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtGenre.getStyleClass().remove("mfx-text-field-error");

        return true;

    }


    public void setBookFormController(BookFormController bookFormController) {
        this.bookFormController = bookFormController;
    }

    public void setBtnAndLblName(String action) {
        btnAction.setText(action);
        lblAction.setText(action + " Book");
    }

    public void loadBookData(String bookId) {


    }
}

