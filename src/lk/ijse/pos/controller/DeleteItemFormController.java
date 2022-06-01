package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.RemoveItemBO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;

public class DeleteItemFormController {
    public TextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;

    private final
    RemoveItemBO removeItemBO= (RemoveItemBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.REMOVEITEM);

    public void itemCodeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1= removeItemBO.searchItem(txtItemCode.getText());
        if (i1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            txtDescription.setText(i1.getDescription());
            txtPackSize.setText(i1.getPackSize());
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
        }
    }

    public void removeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (removeItemBO.removeItem(txtItemCode.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }
}
