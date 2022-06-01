package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.superBO;
import lk.ijse.pos.entity.order;

import java.sql.SQLException;

public interface RemoveOrderBO extends superBO {
    order searchOrder(String Id) throws SQLException, ClassNotFoundException;
    boolean removeOrder(String id) throws SQLException, ClassNotFoundException;
}
