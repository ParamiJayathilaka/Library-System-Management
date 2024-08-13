package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.AdminBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.tm.BookTM;
import lk.ijse.pageController.PageControl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminFormController implements Initializable {

    @FXML
    private JFXButton btnLogout;

    @FXML
    private TableView<BookTM> bookTable;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colStatus;
    PageControl pageControl = new PageControl();
    ObservableList<BookTM> obList = FXCollections.observableArrayList();
    AdminBO adminBO = (AdminBO) BOFactory.getBoFactory().BOTypes(BOFactory.BOTypes.ADMIN);

    @FXML
    public void logoutOnAction(ActionEvent event) throws IOException {
        pageControl.closeWindow(btnLogout);
        pageControl.popUpWindow("/view/loginForm.fxml");
    }

    @FXML
    public void settingOnAction(ActionEvent event) throws IOException {
        pageControl.popUpWindow("/view/settingForm.fxml");
    }

    @FXML
    public void historyOnAction(ActionEvent event) throws IOException {
        pageControl.popUpWindow("/view/historyForm.fxml");
    }

    @FXML
    void branchOnAction(ActionEvent event) throws IOException {
        pageControl.popUpWindow("/view/branchForm.fxml");
    }

    @FXML
    void bookOnAction(ActionEvent event) throws IOException {
        pageControl.popUpWindow("/view/bookForm.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllBooks();
    }

    private void loadAllBooks() {
        try {
            List<BookDTO> bookDTOS = adminBO.getAllBook();
            for (BookDTO bookDTO : bookDTOS){
                obList.add(new BookTM(
                        bookDTO.getId(),
                        bookDTO.getTitle(),
                        bookDTO.getAuthor(),
                        bookDTO.getGenre(),
                        bookDTO.getAvailabilityStatus()
                ));
            }
            bookTable.setItems(obList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("AvailabilityStatus"));
    }
}
