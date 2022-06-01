package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.RemoveOrderBO;
import lk.ijse.pos.entity.order;

import java.sql.SQLException;

public class DeleteOrderFormController {
    public AnchorPane RemoveOrderContext;
    public TextField txtOrderId;
    public JFXTextField txtOrdrDate;
    public JFXTextField txtCustomerId;

    private final RemoveOrderBO removeOrderBO=
            (RemoveOrderBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.REMOVEORDER);

    public void searchOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        order o=removeOrderBO.searchOrder(txtOrderId.getText());

        if (o!=null){
            txtOrdrDate.setText(o.getDate());
            txtCustomerId.setText(o.getCustId());
        }else{
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }
    }

    public void removeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (removeOrderBO.removeOrder(txtOrderId.getText())){
            //updateQtyITEM(txtOrderId.getText());
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }
}
