package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.TableControllBO;
import lk.ijse.pos.view.TM.OrderDetailsTM;
import lk.ijse.pos.view.TM.OrderTM;

import java.sql.SQLException;

public class OrderDetailsFormController {


    public TableView <OrderTM> tblOrder;
    public TableColumn colOrderId;
    public TableColumn colOrderDate;
    public TableColumn colCustId;
    public ComboBox cmbSelectTable;
    public TableView <OrderDetailsTM> tblOrderDetails;
    public TableColumn colTempOrderId;
    public TableColumn colItemCode;
    public TableColumn colQty;
    public TableColumn colDiscount;
    public TableColumn colPrice;

    private final TableControllBO tableControllBO =
            (TableControllBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.CUSTOMERCONTROLLER);

    public void initialize(){
        cmbSelectTable.getItems().addAll("Order","Order Details");
        cmbSelectTable.setValue("Order");

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));

        colTempOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("total"));
        try {
            loadAllOrderDetails();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            loadAllOrder();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllOrder() throws SQLException, ClassNotFoundException {
        tblOrder.setItems(tableControllBO.loadAllOrderTable());
    }

    private void loadAllOrderDetails() throws SQLException, ClassNotFoundException {
        tblOrderDetails.setItems(tableControllBO.loadAllOrderDetailsTable());
    }

    public void selectTabelOnAction(ActionEvent actionEvent) {
        if (cmbSelectTable.getValue().equals("Order Details")) {
            tblOrder.setVisible(false);
            tblOrderDetails.setVisible(true);
        } else {
            tblOrder.setVisible(true);
            tblOrderDetails.setVisible(false);
        }
    }
}
