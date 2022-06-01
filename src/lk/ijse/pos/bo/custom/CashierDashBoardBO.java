package lk.ijse.pos.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.pos.bo.superBO;
import lk.ijse.pos.dto.CustomerOrderDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CashierDashBoardBO extends superBO {
    ResultSet getCustomerOrder(String Id) throws SQLException, ClassNotFoundException;
    List<String> loadCustomerIds() throws SQLException, ClassNotFoundException;
    ObservableList<CustomerOrderDTO> setCustomeOrderItem(String id) throws SQLException, ClassNotFoundException;
    Customer setCustomerData(String customerId) throws SQLException, ClassNotFoundException;
}
