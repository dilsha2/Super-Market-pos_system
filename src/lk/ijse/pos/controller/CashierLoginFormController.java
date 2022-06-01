package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CashierLoginFormController {
    public AnchorPane CashierContext;
    public JFXTextField txtUserName;
    public JFXPasswordField pwdPassword;

    public void logInOnAction(ActionEvent actionEvent) throws IOException {
        if(txtUserName.getText().equals("cashier")& pwdPassword.getText().equals("1234")){
            Stage stage=(Stage) CashierContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CashierDashBoardForm.fxml"))));
            stage.centerOnScreen();
        }else{
            new Alert(Alert.AlertType.WARNING,"User Name or Password Invalid").show();
        }
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) CashierContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.centerOnScreen();
    }
}
