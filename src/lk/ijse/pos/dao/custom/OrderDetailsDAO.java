package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.orderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDetailsDAO extends CrudDAO<orderDetails,String> {
    ResultSet getOrderIncomeItems() throws SQLException, ClassNotFoundException;
    ResultSet getOrderItems(String id) throws SQLException, ClassNotFoundException;
}
