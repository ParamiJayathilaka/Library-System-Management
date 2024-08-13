package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.pageController.PageControl;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Pattern;

public class BookUpdateFormController implements Initializable {
    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXTextField txtBookId;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXTextField txtGenre;

    @FXML
    private JFXTextField txtAvailabilityStatus;

    @FXML
    private JFXTextField searchBook;

    PageControl pageControl = new PageControl();
    BookBO bookBO = (BookBO) BOFactory.getBoFactory().BOTypes(BOFactory.BOTypes.BOOK);
    private List<BookDTO> bookDTOList = bookBO.getAllBook();
    private Set<String> _bookDTOList = new HashSet<>();

    @FXML
    void windowCloseOnAction(ActionEvent event) {
        pageControl.closeWindow(btnClose);
    }

    @FXML
    void viewBookOnAction(ActionEvent event) throws IOException {
        pageControl.popUpWindow("/view/bookForm.fxml");
        pageControl.closeWindow(btnClose);
    }

    @FXML
    void bookUpdateOnAction(ActionEvent event) {

        int bookId = Integer.parseInt(txtBookId.getText());
        String bookName = txtBookName.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        String availableStatus = txtAvailabilityStatus.getText();

        boolean name = Pattern.matches("[a-zA-Z0-9]+",bookName);
        boolean authorName = Pattern.matches("[a-zA-Z0-9]+",author);
        boolean bookGenre = Pattern.matches("[a-zA-Z0-9]+",genre);
        boolean status = Pattern.matches("[a-zA-Z0-9]+",availableStatus);

        var bookDTO = new BookDTO(bookId,bookName,author,genre,availableStatus,null);
        if ( !txtBookId.getText().isEmpty() && name && !txtBookName.getText().isEmpty() && authorName && !txtAuthor.getText().isEmpty() && !txtGenre.getText().isEmpty() && bookGenre && status && !txtAvailabilityStatus.getText().isEmpty()){
            try{
                boolean isUpdate = bookBO.updateBook(bookDTO);

                if (isUpdate){
                    new Alert(Alert.AlertType.INFORMATION,"Update Successfully").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Please try again later!").show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchBooks();
    }

    private void searchBooks() {
        bookDTOList.forEach(bookDTO -> {
            _bookDTOList.add(bookDTO.getTitle());
        });
        TextFields.bindAutoCompletion(searchBook,_bookDTOList);
    }

    @FXML
    void searchBookOnAction(ActionEvent event) {
        setBookDetailsInSearch();
    }

    private void setBookDetailsInSearch() {
        String bookName = searchBook.getText();

        for (int i = 0; i < bookDTOList.size(); i++) {
            if (bookName.equals(bookDTOList.get(i).getTitle())){
                txtBookId.setEditable(false);
                txtBookId.setText(String.valueOf(bookDTOList.get(i).getId()));
                txtBookName.setText(bookDTOList.get(i).getTitle());
                txtAuthor.setText(bookDTOList.get(i).getAuthor());
                txtGenre.setText(bookDTOList.get(i).getGenre());
                txtAvailabilityStatus.setText(bookDTOList.get(i).getAvailabilityStatus());
            }
        }
    }
}
