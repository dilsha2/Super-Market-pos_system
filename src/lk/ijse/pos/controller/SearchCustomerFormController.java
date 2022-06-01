package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.SearchCustomerBO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;

public class SearchCustomerFormController {
    public AnchorPane searchCustomerContext;
    public JFXTextField txtCustId;
    public JFXTextField txtCustName;
    public JFXTextField txtCustTitle;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostCode;

    private final SearchCustomerBO searchCustomerBO=
            (SearchCustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.SEARCHCUSTOMER);

    public void searchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c= searchCustomerBO.searchCustomer(txtCustId.getText());
        if (c==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            txtCustTitle.setVisible(true);
            txtCustName.setVisible(true);
            txtCustAddress.setVisible(true);
            txtCity.setVisible(true);
            txtProvince.setVisible(true);
            txtPostCode.setVisible(true);


            txtCustId.setText(c.getCustId());
            txtCustTitle.setText(c.getCustTitle());
            txtCustName.setText(c.getCustName());
            txtCustAddress.setText(c.getCustAddress());
            txtCity.setText(c.getCity());
            txtProvince.setText(c.getProvince());
            txtPostCode.setText(c.getPostalCode());
        }
    }

}

