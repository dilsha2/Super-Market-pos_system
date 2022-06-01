package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.superBO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends superBO {
    String generateNewOrderId() throws SQLException, ClassNotFoundException;

    boolean placeOrder(OrderDTO dto);

    List<String> loadItemIds() throws SQLException, ClassNotFoundException;

    Item setItemData(String itemId) throws SQLException, ClassNotFoundException;

    void updateItemQTY(String id,int qrt) throws SQLException, ClassNotFoundException;

    Customer setCustomerData(String customerId) throws SQLException, ClassNotFoundException;

    List<String> loadCustomerIds() throws SQLException, ClassNotFoundException;
}
