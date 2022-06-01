package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item,String> {
    ResultSet getCustomerOrderItem(String id) throws SQLException, ClassNotFoundException;
    List<String> getItemIds() throws SQLException, ClassNotFoundException;
    void updateQty(String id,int qty) throws SQLException, ClassNotFoundException;
}
