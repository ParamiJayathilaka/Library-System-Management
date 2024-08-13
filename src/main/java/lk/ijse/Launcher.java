package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BranchDTO;

import java.util.List;

public class Launcher extends Application {
     BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().BOTypes(BOFactory.BOTypes.BRANCH);

    public static void main(String[] args) {
        launch(args);
    }

    private void currentBranches() {
        String branchIdNumber = "001";
        String branchName = "Beruwala";

              try {
                   var branch = new BranchDTO(0, branchIdNumber, branchName);
                   boolean isSaved = branchBO.saveBranches(branch);

              } catch (Exception e) {
                   new Alert(Alert.AlertType.ERROR, "not saved branches");
              }
    }

    @Override
    public void start(Stage stage) throws Exception {
        currentBranches();

        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        if (rootNode != null){
            Scene scene = new Scene(rootNode);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
    }
}
