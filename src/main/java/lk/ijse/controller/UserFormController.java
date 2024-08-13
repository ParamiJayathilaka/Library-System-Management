package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.pageController.PageControl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

    @FXML
    private JFXButton btnLogout;

    @FXML
    private GridPane gridPane;

    @FXML
    private AnchorPane anchorpane;

    PageControl pageControl = new PageControl();

    UserBO userBO = (UserBO) BOFactory.getBoFactory().BOTypes(BOFactory.BOTypes.USER);

    @FXML
    void logoutOnAction(ActionEvent event) throws IOException {
        pageControl.closeWindow(btnLogout);
        pageControl.popUpWindow("/view/loginForm.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridPane.getChildren().clear();

        List<BookDTO> allBooks = userBO.getAllBooks();

        int colomn = 0;
        int row = 0;
        for (int i = 0; i < allBooks.size(); i++) {
            BookOBJController.index = i;
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/bookOBJ.fxml"));
                gridPane.add(parent, colomn, row++);

                GridPane.setMargin(parent, new Insets(5,5,5,5));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void settingOnAction(ActionEvent event) throws IOException {
        pageControl.popUpWindow("/view/settingForm.fxml");
    }

    @FXML
    void userHistoryOnAction(ActionEvent event) throws IOException {
        pageControl.popUpWindow("/view/historyForm.fxml");
    }
}
