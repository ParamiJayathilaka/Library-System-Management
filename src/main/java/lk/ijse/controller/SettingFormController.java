package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.SettingFormBO;
import lk.ijse.dto.AdminDTO;
import lk.ijse.dto.RegisterDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.pageController.PageControl;

import java.util.List;

public class SettingFormController {
    @FXML
    private JFXTextField txtCurrentUsername;

    @FXML
    private JFXTextField txtNewUsername;

    @FXML
    private JFXTextField txtConfirmUsername;

    @FXML
    private JFXTextField txtConfirmPassword;

    @FXML
    private JFXTextField txtNewPassword;

    @FXML
    private JFXTextField txtCurrentPassword;

    @FXML
    private JFXButton btnClose;

    @FXML
    private Label lblCurrentUsername;

    @FXML
    private Label lblNewUsername;

    @FXML
    private Label lblConfirmUsername;

    @FXML
    private Label lblCurrentPassword;

    @FXML
    private Label lblNewPassword;

    @FXML
    private Label lblConfirmPassword;

    PageControl pageControl = new PageControl();

    SettingFormBO settingFormBO = (SettingFormBO) BOFactory.getBoFactory().BOTypes(BOFactory.BOTypes.SETTING);

    @FXML
    void saveChangesOnAction(ActionEvent event) {


        String currentUsername = txtCurrentUsername.getText();
        String newUsername = txtNewUsername.getText();
        String confirmUsername = txtConfirmUsername.getText();

        String currentPassword = txtCurrentPassword.getText();
        String newPassword = txtNewPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        List<AdminDTO> adminDTOList = settingFormBO.getAllAdmin();

        for (int i = 0; i < adminDTOList.size(); i++) {
            if ( txtCurrentUsername.getText().equals(adminDTOList.get(i).getUsername())) {
                if (!currentUsername.isEmpty() && !newUsername.isEmpty() && !confirmUsername.isEmpty()) {

                    boolean isChecked = checkingUsername(confirmUsername, newUsername, confirmUsername);
                    if (isChecked) {
                        var adminDTO = new AdminDTO();
                        adminDTO.setUsername(confirmUsername);
                        boolean isUpdated = settingFormBO.updateAdminUsername(adminDTO);

                        if (isUpdated) {
                            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
                        }
                    }
                } else if (!currentPassword.isEmpty() && !newPassword.isEmpty() && !confirmPassword.isEmpty()) {
                    boolean isChecked = checkingPassword(currentPassword, newPassword, confirmPassword);
                    if (isChecked) {
                        var adminDTO = new AdminDTO();
                        adminDTO.setPassword(confirmPassword);
                        boolean isUpdated = settingFormBO.updateAdminPassword(adminDTO);

                        if (isUpdated) {
                            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
                        }
                    }
                }
            }else {
                if (!currentUsername.isEmpty() && !newUsername.isEmpty() && !confirmUsername.isEmpty()) {

                    boolean isChecked = checkingUsername(currentUsername, newUsername, confirmUsername);
                    if (isChecked) {
                        var userDTO = new UserDTO();
                        userDTO.setName(confirmUsername);
                        boolean isUpdated = settingFormBO.updateUserUsername(userDTO);

                        if (isUpdated) {
                            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
                        }
                    }
                } else if (!currentPassword.isEmpty() && !newPassword.isEmpty() && !confirmPassword.isEmpty()) {
                    boolean isChecked = checkingPassword(currentPassword, newPassword, confirmPassword);
                    if (isChecked) {
                        var userDTO = new UserDTO();
                        userDTO.setPassword(confirmPassword);
                        boolean isUpdated = settingFormBO.updateUserPassword(userDTO);

                        if (isUpdated) {
                            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
                        }
                    }
                }
            }
        }
    }

    private boolean checkingPassword(String currentPassword, String newPassword, String confirmPassword) {
        boolean isChecked = false;
        List<RegisterDTO> registerUsers = settingFormBO.getAllUser();
        List<AdminDTO> adminDTOS = settingFormBO.getAllAdmin();

        for (int i = 0; i < registerUsers.size(); i++) {
            for (int f = 0; f < adminDTOS.size(); f++) {
                if (currentPassword.equals(registerUsers.get(i).getName()) || currentPassword.equals(adminDTOS.get(f).getUsername())) {
                    if (newPassword.equals(confirmPassword)) {
                        isChecked = true;
                    } else {
                        lblNewUsername.setText("incorrect username.please reEnter new username.");
                    }
                } else {
                    lblCurrentUsername.setText("Please input correct username");
                }
            }
        }
        return isChecked;
    }

    private boolean checkingUsername(String currentUsername , String newUsername , String confirmUsername) {
        boolean isChecked = false;
        List<RegisterDTO> registerUsers = settingFormBO.getAllUser();
        List<AdminDTO> adminDTOS = settingFormBO.getAllAdmin();

        for (int i = 0; i < registerUsers.size(); i++) {
            for (int f = 0; f < adminDTOS.size(); f++) {
                if (currentUsername.equals(registerUsers.get(i).getName()) || currentUsername.equals(adminDTOS.get(f).getUsername())) {
                    if (newUsername.equals(confirmUsername)) {
                        isChecked = true;
                    } else {
                        lblNewUsername.setText("incorrect username.please reEnter new username.");
                    }
                } else {
                    lblCurrentUsername.setText("Please input correct username");
                }
            }
        }
        return isChecked;
    }

    @FXML
    void closeWindowOnAction(ActionEvent event) {
        pageControl.closeWindow(btnClose);
    }
}
