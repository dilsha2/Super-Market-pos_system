package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainFormController {
    public AnchorPane mainFormContext;

    public void adminOnAction(ActionEvent actionEvent) throws IOException {
        mainFormContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AdminLogInForm.fxml"));
        mainFormContext.getChildren().add(parent);
    }

    public void cashierOnAction(ActionEvent actionEvent) throws IOException {
        mainFormContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/CashierLoginForm.fxml"));
        mainFormContext.getChildren().add(parent);
    }
}
