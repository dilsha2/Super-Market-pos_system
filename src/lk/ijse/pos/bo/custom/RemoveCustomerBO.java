package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.superBO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface RemoveCustomerBO extends superBO {
    boolean removeCustomer(String id) throws SQLException, ClassNotFoundException;
    Customer setCustomerData(String customerId) throws SQLException, ClassNotFoundException;
    List<String> loadCustomerIds() throws SQLException, ClassNotFoundException;
}
