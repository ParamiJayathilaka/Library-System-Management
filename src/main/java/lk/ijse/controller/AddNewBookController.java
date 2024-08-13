package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.pageController.PageControl;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class AddNewBookController {

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXTextField txtGenre;

    @FXML
    private JFXTextField txtAvailabilityStatus;

    @FXML
    private JFXTextField txtImage;

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().BOTypes(BOFactory.BOTypes.BOOK);
    PageControl pageControl = new PageControl();
    String imagePath;
    @FXML
    void saveBookOnAction(ActionEvent event) {
        String bookName = txtBookName.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        String availableStatus = txtAvailabilityStatus.getText();

        boolean name = Pattern.matches("[a-zA-Z0-9]+", bookName);
        boolean bookAuthor = Pattern.matches("[a-zA-Z0-9]+", author);
        boolean bookGenre = Pattern.matches("[a-zA-Z0-9]+", genre);
        boolean status = Pattern.matches("[a-zA-Z0-9]+", availableStatus);

        if (!txtBookName.getText().isEmpty() && name && !txtAuthor.getText().isEmpty() && bookAuthor && !txtGenre.getText().isEmpty() && bookGenre && !txtAvailabilityStatus.getText().isEmpty() && status) {
            var bookDTO = new BookDTO(0, bookName, author, genre, availableStatus,imagePath);

            try {

                boolean isSaved = bookBO.saveBook(bookDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "added book successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "please try again later").show();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void windowCloseOnAction(ActionEvent event) {
        pageControl.closeWindow(btnClose);
    }

    @FXML
    void viewBookOnAction(ActionEvent event) throws IOException {
        pageControl.closeWindow(btnClose);
        pageControl.popUpWindow("/view/bookForm.fxml");
    }

    @FXML
    void btnChooseImage(ActionEvent event) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image file");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imagePath = file.getPath();
        }
    }
}
