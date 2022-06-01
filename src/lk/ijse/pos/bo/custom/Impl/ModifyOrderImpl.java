package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.ModifyOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.orderDetails;

import java.sql.SQLException;

public class ModifyOrderImpl implements ModifyOrderBO {
    private final OrderDetailsDAO orderDetailsDAO =
            (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    @Override
    public boolean updateOrder(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.update(new orderDetails(dto.getOrderID(),dto.getItemCode(),dto.getOrderQTY(),
                dto.getDiscont(),dto.getTotal()));
    }

    @Override
    public orderDetails searchOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.search(id);
    }
}
