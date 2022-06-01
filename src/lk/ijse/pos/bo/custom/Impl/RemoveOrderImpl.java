package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.RemoveOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.entity.order;

import java.sql.SQLException;

public class RemoveOrderImpl implements RemoveOrderBO {
    private final OrderDAO orderDAO= (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public order searchOrder(String Id) throws SQLException, ClassNotFoundException {
        return orderDAO.search(Id);
    }

    @Override
    public boolean removeOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }
}
