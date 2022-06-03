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
import lk.ijse.pos.bo.custom.AddNewCustomerBO;
import lk.ijse.pos.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddNewCustomerFormController {
    public AnchorPane AddCustomerContext;
    public JFXTextField txtCustId;
    public JFXTextField txtCustName;
    public JFXTextField txtCustTitle;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;
    public JFXButton btnAdd;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    private final AddNewCustomerBO addNewCustomerBO= (AddNewCustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ADDCUSTOMER);


    public void initialize(){
        Pattern TitlePattern = Pattern.compile("^[A-z .]{3,}$");
        Pattern NamePattern = Pattern.compile("^[A-z]{3,20}$");
        Pattern AddressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern CityPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern ProvincePattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern postalCodePattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");

        //add pattern and text to the map
        map.put(txtCustTitle,TitlePattern);
        map.put(txtCustName,NamePattern);
        map.put(txtCustAddress,AddressPattern);
        map.put(txtCity,CityPattern);
        map.put(txtProvince,ProvincePattern);
        map.put(txtPostalCode,postalCodePattern);
    }
    public void saveCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO dto = new CustomerDTO(txtCustId.getText(),txtCustTitle.getText(),txtCustName.getText(),txtCustAddress.getText(),
                txtCity.getText(),txtProvince.getText(),txtPostalCode.getText());

        if (addNewCustomerBO.addNewCustomer(dto)){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            clear();
        }else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }

    private void clear() {
        txtCustId.clear();
        txtCustTitle.clear();
        txtCustName.clear();
        txtCustAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
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
