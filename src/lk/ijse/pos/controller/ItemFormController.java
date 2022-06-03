package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.Util.ValidationUtil;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.AddNewItemBO;
import lk.ijse.pos.dto.ItemDTO;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ItemFormController {
    public AnchorPane itemContext;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtPrice;
    public JFXTextField txtQty;
    public JFXButton btnAdd;
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    private final AddNewItemBO addNewItemBO= (AddNewItemBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ADDITEM);


    public void initialize(){
        Pattern DescriptionPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern PackSizePattern = Pattern.compile("^[A-z]{3,20}$");
        Pattern UnitPricePattern = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");
        Pattern QtyOnHand = Pattern.compile("^[0-9]{1,20}$");



        //add pattern and text to the map
        map.put(txtDescription,DescriptionPattern);
        map.put(txtPackSize,PackSizePattern);
        map.put(txtPrice,UnitPricePattern);
        map.put(txtQty,QtyOnHand);
    }
    public void addNewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO dto = new ItemDTO(
                txtCode.getText(),txtDescription.getText(),txtPackSize.getText(),
                Double.parseDouble(txtPrice.getText()),Integer.parseInt(txtQty.getText())
        );

        if(addNewItemBO.addItem(dto)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();

            clear();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    private void clear() {
        txtCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtPrice.clear();
        txtQty.clear();
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAdd);

        if (keyEvent.getCode()== KeyCode.ENTER){
            Object response = ValidationUtil.validate(map,btnAdd);
            if (response instanceof JFXTextField){
                JFXTextField textField = (JFXTextField) response;
                textField.requestFocus();
            }else if (response instanceof Boolean){

            }
        }
    }
}
