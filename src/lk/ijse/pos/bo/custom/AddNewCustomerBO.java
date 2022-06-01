package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.superBO;
import lk.ijse.pos.dto.CustomerDTO;

import java.sql.SQLException;

public interface AddNewCustomerBO extends superBO {
    boolean addNewCustomer(CustomerDTO dto)throws SQLException, ClassNotFoundException ;
}
