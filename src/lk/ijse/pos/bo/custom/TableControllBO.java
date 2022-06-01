package lk.ijse.pos.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.pos.bo.superBO;
import lk.ijse.pos.view.TM.CustomerTM;
import lk.ijse.pos.view.TM.ItemTM;
import lk.ijse.pos.view.TM.OrderDetailsTM;
import lk.ijse.pos.view.TM.OrderTM;

import java.sql.SQLException;

public interface TableControllBO extends superBO {
    ObservableList<CustomerTM> loadAllCustomerTable() throws SQLException, ClassNotFoundException;
    ObservableList<ItemTM> loadAllItemTable() throws SQLException, ClassNotFoundException;
    ObservableList<OrderTM> loadAllOrderTable() throws SQLException, ClassNotFoundException;
    ObservableList<OrderDetailsTM> loadAllOrderDetailsTable() throws SQLException, ClassNotFoundException;
}
