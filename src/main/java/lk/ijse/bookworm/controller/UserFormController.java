package lk.ijse.bookworm.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.tm.UserTm;

import java.io.IOException;

public class UserFormController {

    @FXML
    private TableColumn<UserTm, String> colAddress;

    @FXML
    private TableColumn<UserTm, String> colEmail;

    @FXML
    private TableColumn<UserTm, String> colName;

    @FXML
    private TableColumn<UserTm, String> colRemove;

    @FXML
    private TableColumn<UserTm, String> colUpdate;

    @FXML
    private TableView<UserTm> tblUser;


    @FXML
    private AnchorPane userPane;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void initialize(){
        setCellValueFactory();
        loadUserDetails();
    }

    private void loadUserDetails() {
        userBO.getAllUsers().forEach(userDto -> {
            tblUser.getItems().add(new UserTm(userDto.getName(),userDto.getEmail(),userDto.getAddress()));
        });
    }

    private void setCellValueFactory() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));



        Callback<TableColumn<UserTm, String>, TableCell<UserTm, String>> colRemoveCellFactory
                = new Callback<TableColumn<UserTm, String>, TableCell<UserTm, String>>()
        {
            @Override
            public TableCell<UserTm,String> call(final TableColumn<UserTm, String> param)
            {
                final TableCell<UserTm, String> cell = new TableCell<UserTm, String>()
                {
                    final MFXButton btn = new MFXButton("Remove");

                    {
                        btn.setOnAction(event -> {
                            UserTm tm = getTableView().getItems().get(getIndex());

                            boolean b = userBO.deleteUser(tm.getEmail());
                            System.out.println(b);
                            System.out.println(tm.getEmail());

                            tblUser.getItems().remove(getIndex());
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



        Callback<TableColumn<UserTm, String>, TableCell<UserTm, String>> colUpdateCellFactory
                = new Callback<>() {
            @Override
            public TableCell<UserTm, String> call(final TableColumn<UserTm, String> param) {
                final TableCell<UserTm, String> cell = new TableCell<>() {
                    final MFXButton btn = new MFXButton("Update");

                    {
                        btn.setOnAction(event -> {
                            UserTm tm = getTableView().getItems().get(getIndex());
                            System.out.println(tm.getEmail());

                            tblUser.getItems().remove(getIndex());
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
    void btnAddOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/UserDataForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Add User");
        stage.show();
    }
}

