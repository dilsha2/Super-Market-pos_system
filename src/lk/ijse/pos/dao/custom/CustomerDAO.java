package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    List<String> getCustomerIds() throws SQLException, ClassNotFoundException;
}
