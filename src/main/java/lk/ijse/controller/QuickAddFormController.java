package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.TransactionDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.pageController.PageControl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class QuickAddFormController implements Initializable {

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXDatePicker txtStartDate;

    @FXML
    private JFXDatePicker txtReturnDate;

    @FXML
    private JFXTextField txtTitle;

    @FXML
    private JFXTimePicker txtTime;

    PageControl pageControl = new PageControl();
    TransactionBO transactionBO = (TransactionBO) BOFactory.getBoFactory().BOTypes(BOFactory.BOTypes.TRANSACTION);


    static String title;
    public static long bookId;

    @FXML
    void closeWindowOnAction(ActionEvent event) throws IOException {
        pageControl.closeWindow(btnClose);
    }

    @FXML
    void quickAddOnAction(ActionEvent event) {
        UserDTO userDTO = null;
        BookDTO bookDTO = null;

        String startDate = String.valueOf(txtStartDate.getValue());
        String time = String.valueOf(txtTime.getValue());
        String returnDate = String.valueOf(txtReturnDate.getValue());
        String name = txtName.getText();
        String title = txtTitle.getText();

        for(UserDTO users : transactionBO.getAllUser()){
             userDTO = new UserDTO(users.getId(),users.getName(),users.getEmail(),users.getBranch(),users.getPassword());
        }

        for (BookDTO books : transactionBO.getAllBooks()){
              bookDTO = new BookDTO(books.getId(),books.getTitle(),books.getAuthor(),books.getGenre(),books.getAvailabilityStatus());
        }

        boolean nameUser = Pattern.matches("[a-zA-Z]+",name);
        boolean bookTitle = Pattern.matches("[a-zA-Z0-9]+",title);

        var transactionDTO = new TransactionDTO(0,name,title,startDate,returnDate,time);
        if (nameUser && bookTitle){
            boolean isSaved = transactionBO.saveTransaction(transactionDTO,userDTO,bookDTO);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Quick Add Book Successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"please try Again later").show();
            }
        }else {

        }
    }

    private void setBookTitle() {
       txtTitle.setText(title);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBookTitle();
    }
}
