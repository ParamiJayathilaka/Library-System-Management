package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.tm.BookTM;
import lk.ijse.pageController.PageControl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookFormController implements Initializable {
    @FXML
    private JFXButton btnClose;

    @FXML
    private TableView<BookTM> bookTable;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colAvailability_Status;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableColumn<?, ?> colDelete;

    PageControl pageControl = new PageControl();
    BookBO bookBO = (BookBO) BOFactory.getBoFactory().BOTypes(BOFactory.BOTypes.BOOK);
    ObservableList<BookTM> obList = FXCollections.observableArrayList();

    @FXML
    void closeOnAction(ActionEvent event) {
        pageControl.closeWindow(btnClose);
    }

    @FXML
    void newBookOnAction(ActionEvent event) throws IOException {
        pageControl.popUpWindow("/view/addNewBook.fxml");
        pageControl.closeWindow(btnClose);
    }

    public void loadAllBooks(){
        try {
            List<BookDTO> bookDTOS = bookBO.getAllBook();
            for (BookDTO bookDTO : bookDTOS){
                obList.add(new BookTM(
                    bookDTO.getId(),
                    bookDTO.getTitle(),
                    bookDTO.getAuthor(),
                    bookDTO.getGenre(),
                    bookDTO.getAvailabilityStatus(),
                    new JFXButton("Update"),
                    new JFXButton("Delete")
                ));
            }
            bookTable.setItems(obList);

            for (int i = 0; i < obList.size(); i++) {
                obList.get(i).getDelete().setTextFill(Color.WHITE);
                obList.get(i).getDelete().setStyle("-fx-background-color: red");
                obList.get(i).getDelete().setAlignment(Pos.CENTER);
                obList.get(i).getUpdate().setTextFill(Color.WHITE);
                obList.get(i).getUpdate().setStyle("-fx-background-color: green");
                obList.get(i).getUpdate().setAlignment(Pos.CENTER);

                obList.get(i).getUpdate().setOnAction(event->{
                    try{
                        pageControl.popUpWindow("/view/BookUpdateForm.fxml");
                        pageControl.closeWindow(btnClose);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    bookTable.refresh();
                });
            }

            bookTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
                for (int i = 0; i < obList.size(); i++) {
                    obList.get(i).getDelete().setOnAction(event -> {

                        ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                        Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure delete Book!", buttonYes, buttonNo).showAndWait();
                        if (type.get().getText().equals("Yes")) {

                            try {
                                boolean isDelete = bookBO.deleteBook(newSelection.getId());

                                if (isDelete) {
                                    new Alert(Alert.AlertType.INFORMATION, "Delete Successfully").show();
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Please Try again later!").show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        colAvailability_Status.setCellValueFactory(new PropertyValueFactory<>("AvailabilityStatus"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("Update"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("Delete"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllBooks();
        setCellValueFactory();
    }
}
