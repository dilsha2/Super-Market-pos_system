package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.superBO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;

public interface RemoveItemBO extends superBO {
    Item searchItem(String Id) throws SQLException, ClassNotFoundException;
    boolean removeItem(String id) throws SQLException, ClassNotFoundException;
}
