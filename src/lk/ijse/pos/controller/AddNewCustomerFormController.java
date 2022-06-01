package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.AddNewCustomerBO;
import lk.ijse.pos.dto.CustomerDTO;

import java.sql.SQLException;

public class AddNewCustomerFormController {
    public AnchorPane AddCustomerContext;
    public JFXTextField txtCustId;
    public JFXTextField txtCustName;
    public JFXTextField txtCustTitle;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;

    private final AddNewCustomerBO addNewCustomerBO= (AddNewCustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ADDCUSTOMER);

    public void saveCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO dto = new CustomerDTO(txtCustId.getText(),txtCustTitle.getText(),txtCustName.getText(),txtCustAddress.getText(),
                txtCity.getText(),txtProvince.getText(),txtPostalCode.getText());

        if (addNewCustomerBO.addNewCustomer(dto)){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        }else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }
