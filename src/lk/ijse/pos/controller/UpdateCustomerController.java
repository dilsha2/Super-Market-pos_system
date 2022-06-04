package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.UpdateCustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateCustomerController {
    public AnchorPane updateCustomerContext;
    public JFXTextField txtCustId;
    public JFXTextField txtCustName;
    public JFXTextField txtCustTitle;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostCode;

    private final UpdateCustomerBO editCustomerBO=
            (UpdateCustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.EDITCUSTOMER);

    public void updateOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        CustomerDTO dto= new CustomerDTO(txtCustId.getText(),txtCustTitle.getText(),txtCustName.getText(),
                txtCustAddress.getText(),txtCity.getText(),txtProvince.getText(),txtPostCode.getText());
        if (editCustomerBO.editCustomer(dto))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
    }

    public void customerIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtCustId.getText();

        Customer c1= editCustomerBO.searchCustomer(customerId);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }
    }

    private void setData(Customer c) {
        txtCustId.setText(c.getCustId());
        txtCustTitle.setText(c.getCustTitle());
        txtCustName.setText(c.getCustName());
        txtCustAddress.setText(c.getCustAddress());
        txtCity.setText(c.getCity());
        txtProvince.setText(c.getProvince());
        txtPostCode.setText(c.getPostalCode());
    }
}
