package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RegisterFormBO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.dto.RegisterDTO;
import lk.ijse.pageController.PageControl;
import org.apache.commons.codec.binary.Base64;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Pattern;

public class RegisterFormController implements Initializable {

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtBranchName;

    @FXML
    private Label lblBranch;

    @FXML
    private AnchorPane registerAnchorpane;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPassword;

    PageControl pageControl = new PageControl();
    RegisterFormBO registerFormBO = (RegisterFormBO) BOFactory.getBoFactory().BOTypes(BOFactory.BOTypes.REGISTER);

    private List<BranchDTO>branchDTOList = registerFormBO.getAllBranches();
    private Set<String> _branchDTOList = new HashSet<>();

    @FXML
    void loginOnAction(MouseEvent event) throws IOException {
        pageControl.changeOnlyAnchorPane("/view/loginForm.fxml" , registerAnchorpane);
    }

    @FXML
    void registerOnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String branchName = txtBranchName.getText();

        boolean name = Pattern.matches("[a-zA-Z0-9]+",username);
        boolean mail = Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",email);
        boolean pass = Pattern.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",password);
        boolean branch = Pattern.matches("[a-zA-Z0-9]+",branchName);
        pageControl.hiddenErrorMessage(lblUsername,lblEmail,lblBranch,lblPassword , name,mail,pass,branch);

        if (!username.isEmpty() && name && !email.isEmpty() && mail && !password.isEmpty() && pass && !branchName.isEmpty() && branch){
            String encryptPassword = encryptPassword(password);
            var registerDTO = new RegisterDTO(0, username, email, encryptPassword, branchName);

            try {
                 boolean isSaved = registerFormBO.saveUser(registerDTO);
                 if (isSaved){
                     pageControl.clearTextField(txtUsername,txtPassword,txtEmail,txtBranchName);
                     new Alert(Alert.AlertType.INFORMATION,"Save Successfully!").show();
                 }else {
                     new Alert(Alert.AlertType.ERROR,"Try again!").show();
                 }

            }catch (Exception e){
               e.printStackTrace();
           }

        }else {
            if(!username.isEmpty() && !name){
                lblUsername.setText("Please input correct username");
            }
            if (!email.isEmpty() && !mail) {
                lblEmail.setText("Please enter correct email address");
            }
            if (!password.isEmpty() && !pass){
                lblPassword.setText("Please enter a strong password with capital letters,\nsimple letters, symbols and numbers and 8 characters.");
            }
            if (!branchName.isEmpty() && !branch){
                lblBranch.setText("Please select available branch");
            }
        }
    }

    private String encryptPassword(String text) {
        return new String(Base64.encodeBase64(text.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectBranch();
    }

    private void selectBranch() {
        branchDTOList.forEach(registerDTO ->{
            _branchDTOList.add(registerDTO.getBranchName());
        });
        TextFields.bindAutoCompletion(txtBranchName, _branchDTOList);
    }
}
