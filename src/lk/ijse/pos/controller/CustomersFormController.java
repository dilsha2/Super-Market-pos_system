package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.TableControllBO;
import lk.ijse.pos.view.TM.CustomerTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class CustomersFormController {
    public AnchorPane CustomerContext;
    public TableView <CustomerTM>tblAllCustomers;
    public TableColumn colCid;
    public TableColumn colTitle;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostCode;

    private final TableControllBO tableControllBO =
            (TableControllBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.CUSTOMERCONTROLLER);

    public void initialize(){
        colCid.setCellValueFactory(new PropertyValueFactory("CustId"));
        colTitle.setCellValueFactory(new PropertyValueFactory("CustTitle"));
        colName.setCellValueFactory(new PropertyValueFactory("CustName"));
        colAddress.setCellValueFactory(new PropertyValueFactory("CustAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory("province"));
        colPostCode.setCellValueFactory(new PropertyValueFactory("postalCode"));

        try {
            setCustomersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomersTable() throws SQLException, ClassNotFoundException {
        tblAllCustomers.setItems(tableControllBO.loadAllCustomerTable());
    }

    public void addNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddNewCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        CustomerContext.getChildren().clear();
        CustomerContext.getChildren().add(load);
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/UpdateCustomer.fxml");
        Parent load = FXMLLoader.load(resource);
        CustomerContext.getChildren().clear();
        CustomerContext.getChildren().add(load);
    }

    public void searchCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SearchCutomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        CustomerContext.getChildren().clear();
        CustomerContext.getChildren().add(load);
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DeleteCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        CustomerContext.getChildren().clear();
        CustomerContext.getChildren().add(load);
    }
}
