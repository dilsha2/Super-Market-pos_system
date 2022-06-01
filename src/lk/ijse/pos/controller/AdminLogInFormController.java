package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.log.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminLogInFormController {
    public AnchorPane LoginContext;
    public JFXTextField txtUserName;
    public JFXPasswordField pwdPassword;

    public void logInOnAction(ActionEvent actionEvent) throws IOException {
        if(txtUserName.getText().equals("admin")& pwdPassword.getText().equals("1234")){
            Stage stage=(Stage) LoginContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashBoardForm.fxml"))));
            stage.centerOnScreen();
        }else{
            new Alert(Alert.AlertType.WARNING,"User Name or Password Invalid").show();
        }
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) LoginContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.centerOnScreen();
    }
}
