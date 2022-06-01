package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.superBO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.orderDetails;

import java.sql.SQLException;

public interface ModifyOrderBO extends superBO {
    boolean updateOrder(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException;
    orderDetails searchOrder(String id) throws SQLException, ClassNotFoundException;
}
