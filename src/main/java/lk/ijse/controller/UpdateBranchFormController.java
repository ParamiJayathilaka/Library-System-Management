package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.pageController.PageControl;

import java.io.IOException;
import java.util.regex.Pattern;

public class UpdateBranchFormController {
    @FXML
    private JFXTextField txtBranchNumber;

    @FXML
    private JFXTextField txtBranchName;

    @FXML
    private JFXTextField searchBranch;

    @FXML
    private JFXTextField txtBranchId;

    @FXML
    private JFXButton btnClose;

    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().BOTypes(BOFactory.BOTypes.BRANCH);
    PageControl pageControl = new PageControl();
    @FXML
    void branchUpdateOnAction(ActionEvent event) {
        int id = Integer.parseInt(txtBranchId.getText());
        String name = txtBranchName.getText();
        String branchNumber = txtBranchNumber.getText();

        boolean branchIdNumber = Pattern.matches("[a-zA-Z0-9]+",branchNumber);
        boolean bName = Pattern.matches("[a-zA-Z0-9]+",name);

        var branchDTO = new BranchDTO(id,name,branchNumber);
        if (!txtBranchId.getText().isEmpty() && branchIdNumber && !txtBranchName.getText().isEmpty() && bName && !txtBranchNumber.getText().isEmpty()){
            try{
                boolean isUpdated = branchBO.updateBranches(branchDTO);
                if (isUpdated){
                    new Alert(Alert.AlertType.INFORMATION,"Update successfully").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"please try again later").show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void searchBranchOnAction(ActionEvent event) {

    }

    @FXML
    void viewBranchOnAction(ActionEvent event) throws IOException {
        pageControl.popUpWindow("/view/BranchForm.fxml");
        pageControl.closeWindow(btnClose);
    }

    @FXML
    void closeWindowOnAction(ActionEvent event) {
        pageControl.closeWindow(btnClose);
    }
}
