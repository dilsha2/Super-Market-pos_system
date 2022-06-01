package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.order;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<order,String> {
    ResultSet getCustomerOrder(String Id) throws SQLException, ClassNotFoundException;
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
}
