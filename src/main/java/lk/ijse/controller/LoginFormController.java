package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.AdminBO;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.AdminDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.pageController.PageControl;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private AnchorPane loginAnchorpane;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblPassword;

    PageControl pageControl = new PageControl();
    AdminBO adminBO = (AdminBO) BOFactory.getBoFactory().BOTypes(BOFactory.BOTypes.ADMIN);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().BOTypes(BOFactory.BOTypes.USER);

    static int increment = 0;

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {

        List<AdminDTO> allAdmin = adminBO.getAllAdmin();
        List<UserDTO> allUsers = userBO.getAllUsers();

        for (AdminDTO adminDTO : allAdmin){
            for (UserDTO userDTO : allUsers) {

                String password = encryptPassword(txtPassword.getText());
                if (!txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty() && adminDTO.getUsername().equals(txtUsername.getText()) && adminDTO.getPassword().equals(txtPassword.getText())) {
                    pageControl.popUpWindow("/view/adminForm.fxml");
                    pageControl.closeWindow(btnClose);
                } else if (!txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty() && userDTO.getName().equals(txtUsername.getText()) && userDTO.getPassword().equals(password)) {
                    pageControl.popUpWindow("/view/userForm.fxml");
                    pageControl.closeWindow(btnClose);
                }
            }
        }

        if (increment==0){
            createAdmin();
        }
        increment++;
    }

    private String encryptPassword(String text) {
        return new String(Base64.encodeBase64(text.getBytes(StandardCharsets.UTF_8)));
    }

    public void registerOnAction(ActionEvent event) throws IOException {
        pageControl.changeOnlyAnchorPane("/view/registerForm.fxml" , loginAnchorpane);
    }

    @FXML
    void closeButtonOnAction(ActionEvent event) {
        pageControl.closeWindow(btnClose);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void createAdmin() {
        String username = "admin";
        String password = "1234";

        try{
            var adminDTO = new AdminDTO(0,username,password);
            boolean isSaved = adminBO.saveAdmin(adminDTO);

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Not saved admin");
        }
    }
}
