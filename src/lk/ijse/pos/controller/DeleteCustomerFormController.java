package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.RemoveCustomerBO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;

public class DeleteCustomerFormController {
    public AnchorPane deleteCustomerContext;
    public ComboBox <String>cmbCustomerId;
    public JFXTextField txtCustName;
    public JFXTextField txtCustTitle;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostCode;

    private final RemoveCustomerBO removeCustomerBO= (RemoveCustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.REMOVECUSTOMER);

    public void initialize() throws SQLException, ClassNotFoundException {

        loadCustomerId();

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setData(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setData(String customerId) throws SQLException, ClassNotFoundException {
        Customer c1 = removeCustomerBO.setCustomerData(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtCustTitle.setText(c1.getCustTitle());
            txtCustName.setText(c1.getCustName());
            txtCustAddress.setText(c1.getCustAddress());
            txtCity.setText(c1.getCity());
            txtProvince.setText(c1.getProvince());
            txtPostCode.setText(c1.getPostalCode());
        }
    }

    private void loadCustomerId() throws SQLException, ClassNotFoundException {
        cmbCustomerId.getItems().addAll(removeCustomerBO.loadCustomerIds());
    }

    public void removeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        txtCity.clear();
        txtPostCode.clear();
        txtProvince.clear();
        txtCustAddress.clear();
        txtCustName.clear();
        txtCustTitle.clear();


        if (removeCustomerBO.removeCustomer(cmbCustomerId.getValue())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();

        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

}
