package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.superBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;

public interface UpdateCustomerBO extends superBO {
    boolean editCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    Customer searchCustomer(String id) throws SQLException, ClassNotFoundException;
}
