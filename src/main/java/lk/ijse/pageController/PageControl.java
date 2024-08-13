package lk.ijse.pageController;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PageControl {
    public void changeOnlyAnchorPane(String fxml , AnchorPane anchorPaneId) throws IOException {
        FXMLLoader fxmlLoader = new  FXMLLoader(getClass().getResource(fxml));
        AnchorPane anchorPane = fxmlLoader.load();
        anchorPaneId.getChildren().removeAll();
        anchorPaneId.getChildren().setAll(anchorPane);
    }

    public void popUpWindow(String fxml) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void closeWindow(JFXButton btnId){
        Stage stage = (Stage) btnId.getScene().getWindow();
        stage.close();
    }

    public void clearTextField(TextField...txtFieldId){
       for (TextField txtFields : txtFieldId){
          txtFields.clear();
       }
    }

    public void hiddenErrorMessage(Label lblUsername , Label lblEmail , Label lblBranches , Label lblPassword, boolean...userInfo) {
        for (boolean userINFO : userInfo){
            if (userINFO){
                lblUsername.setText("");
                lblEmail.setText("");
                lblBranches.setText("");
                lblPassword.setText("");
            }
        }
    }
}
