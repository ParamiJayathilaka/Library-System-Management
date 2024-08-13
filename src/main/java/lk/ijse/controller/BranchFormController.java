package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.dto.tm.BranchTM;
import lk.ijse.pageController.PageControl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class BranchFormController implements Initializable {
    @FXML
    private TableView<BranchTM> branchTable;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colBranchId;

    @FXML
    private TableColumn<?, ?> colBranchName;

    @FXML
    private TableColumn<?, ?> colBranchUpdate;

    @FXML
    private TableColumn<?, ?> colBranchDelete;

    @FXML
    private JFXButton btnClose;

    PageControl pageControl = new PageControl();
    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().BOTypes(BOFactory.BOTypes.BRANCH);
    ObservableList<BranchTM> obList = FXCollections.observableArrayList();

    public void loadAllBranch(){
        try{
            List<BranchDTO> branchDTO = branchBO.getAllBranches();
            for (BranchDTO allBranch : branchDTO){
                obList.add(new BranchTM(
                    allBranch.getId(),
                    allBranch.getBranchIdNumber(),
                    allBranch.getBranchName(),
                    new JFXButton("Update"),
                    new JFXButton("Delete")
                ));
            }
            branchTable.setItems(obList);

            for (int i = 0; i < obList.size(); i++){
                obList.get(i).getUpdate().setOnAction(event -> {
                    try{
                        pageControl.popUpWindow("/view/updateBranchForm.fxml");
                        pageControl.closeWindow(btnClose);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    branchTable.refresh();
                });
            }

            branchTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)-> {
                for (int i = 0; i < obList.size(); i++) {
                    obList.get(i).getDelete().setOnAction(event -> {
                        ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                        Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure delete Book!", buttonYes, buttonNo).showAndWait();
                        if (type.get().getText().equals("Yes")) {

                            try {
                                boolean isDelete = branchBO.deleteBranches(newSelection.getId());

                                if (isDelete) {
                                    new Alert(Alert.AlertType.INFORMATION, "Delete Successfully").show();
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Please Try again later!").show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBranchId.setCellValueFactory(new PropertyValueFactory<>("branchIdNumber"));
        colBranchName.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colBranchUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colBranchDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }

    @FXML
    void addNewBranchOnAction(ActionEvent event) throws IOException {
        pageControl.popUpWindow("/view/addNewBranch.fxml");
        pageControl.closeWindow(btnClose);
    }

    @FXML
    void closeOnAction(ActionEvent event) {
        pageControl.closeWindow(btnClose);
    }

    @FXML
    void viewBranchOnAction(ActionEvent event) throws IOException {
        pageControl.popUpWindow("/view/BranchForm.fxml");
        pageControl.closeWindow(btnClose);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllBranch();
    }
}
