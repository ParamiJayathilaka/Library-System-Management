package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookOBJBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.pageController.PageControl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookOBJController implements Initializable {
    @FXML
    private ImageView imageView;
    public static int index;

    @FXML
    private Label lblBookTitle;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblGenre;

    @FXML
    private Label lblStatus;

    @FXML
    private JFXButton btnQuickAdd;

    BookOBJBO bookOBJBO = (BookOBJBO) BOFactory.getBoFactory().BOTypes(BOFactory.BOTypes.BOOK_OBJ);
    PageControl pageControl = new PageControl();
    List<BookDTO> books = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnQuickAdd.setVisible(false);
        setDataInGridPane();
    }

    private void setDataInGridPane() {
        books = bookOBJBO.getAllBooks();

        Image image = new Image(books.get(index).getImage());
        ImageView imageView1 = new ImageView(image);

        imageView.setImage(imageView1.getImage());
        lblBookTitle.setText(books.get(index).getTitle());
        lblAuthor.setText(books.get(index).getAuthor());
        lblGenre.setText(books.get(index).getGenre());
        lblStatus.setText(books.get(index).getAvailabilityStatus());
    }

    @FXML
    void quickAddLabelMouseEnterOnAction(MouseEvent event) {
        for (BookDTO bookDTO : bookOBJBO.getAllBooks()){
            if (bookDTO.getAvailabilityStatus().equals("Not Available")){
                btnQuickAdd.setVisible(false);
            }else {
                btnQuickAdd.setVisible(true);
            }
        }
    }

    @FXML
    void quickAddLabelMouseExitOnAction(MouseEvent event) {
        btnQuickAdd.setVisible(false);
    }

    @FXML
    void btnQuickAddOnAction(ActionEvent event) throws IOException {
        getClickBookDetails();
        pageControl.popUpWindow("/view/quickAddForm.fxml");
    }

    private void getClickBookDetails() {
        try {
            Optional<BookDTO> first = books.stream().filter(books1 -> books1.getTitle() == lblBookTitle.getText()).findFirst();
            QuickAddFormController.title = first.get().getTitle();
            QuickAddFormController.bookId = first.get().getId();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
