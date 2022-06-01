package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.superBO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;

public interface SearchCustomerBO extends superBO {
    Customer searchCustomer(String id) throws SQLException, ClassNotFoundException;
}
