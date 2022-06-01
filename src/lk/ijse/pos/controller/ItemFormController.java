package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.AddNewItemBO;
import lk.ijse.pos.dto.ItemDTO;

import java.sql.SQLException;

public class ItemFormController {
    public AnchorPane itemContext;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtPrice;
    public JFXTextField txtQty;

    private final AddNewItemBO addNewItemBO= (AddNewItemBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ADDITEM);

    public void addNewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO dto = new ItemDTO(
                txtCode.getText(),txtDescription.getText(),txtPackSize.getText(),
                Double.parseDouble(txtPrice.getText()),Integer.parseInt(txtQty.getText())
        );

        if(addNewItemBO.addItem(dto))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

}
