package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ModifyOrderBO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.orderDetails;

import java.sql.SQLException;

public class ModifyOrderFormController {
    public AnchorPane modifyOrderContext;
    public TextField txtOrderId;
    public JFXTextField txtItemCode;
    public JFXTextField txtQty;
    public JFXTextField txtDiscount;
    double fullTotal;
    double newTotal;

    private final ModifyOrderBO modifyOrderBO= (ModifyOrderBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.MODIFYORDER);


    public void confirmOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        OrderDetailsDTO i1= new OrderDetailsDTO(txtItemCode.getText(),Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtDiscount.getText()),newTotal);


        if (modifyOrderBO.updateOrder(i1))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
    }

    public void txtSearchOrderIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String OrderID = txtOrderId.getText();

        orderDetails o = modifyOrderBO.searchOrder(OrderID);
        if (o == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            txtItemCode.setText(o.getItemCode());
            txtQty.setText(String.valueOf(o.getOrderQty()));
            txtDiscount.setText(String.valueOf(o.getDiscount()));
            fullTotal = (o.getTotal() * 100) / (100 - Double.parseDouble(txtDiscount.getText()));
        }
    }
        void setData(orderDetails o) {
            txtItemCode.setText(o.getItemCode());
            txtQty.setText(String.valueOf(o.getOrderQty()));
            txtDiscount.setText(String.valueOf(o.getDiscount()));

            fullTotal=(o.getTotal()*100)/(100-Double.parseDouble(txtDiscount.getText()));
    }
}
