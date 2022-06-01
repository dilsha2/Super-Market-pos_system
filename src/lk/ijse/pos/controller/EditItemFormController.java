package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ModifyItemBO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;

public class EditItemFormController {
    public ComboBox <String>cmbItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;

    private final ModifyItemBO modifyItemBO= (ModifyItemBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.MODIFYITEM);

    public void initialize(){

        try {
            loadItemId();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setValues(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setValues(String itemCode) throws SQLException, ClassNotFoundException {
        Item i1 =modifyItemBO.getItem(itemCode) ;
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtDescription.setText(i1.getDescription());
            txtPackSize.setText(i1.getPackSize());
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));

        }
    }

    private void loadItemId() throws SQLException, ClassNotFoundException {
        cmbItemCode.getItems().addAll(modifyItemBO.loadItemIds());
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO i1= new ItemDTO((String) cmbItemCode.getValue(),txtDescription.getText(),txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQtyOnHand.getText())
        );
        if (modifyItemBO.addNewItem(i1))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();

    }
}
