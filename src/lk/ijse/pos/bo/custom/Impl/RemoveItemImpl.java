package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.RemoveItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;

public class RemoveItemImpl implements RemoveItemBO {
    private final ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public Item searchItem(String Id) throws SQLException, ClassNotFoundException {
        return itemDAO.search(Id);
    }

    @Override
    public boolean removeItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }
}
