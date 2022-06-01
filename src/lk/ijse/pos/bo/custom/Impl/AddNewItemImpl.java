package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.AddNewItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;

public class AddNewItemImpl implements AddNewItemBO {

    private final ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public boolean addItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(dto.getItemID(),dto.getItenDescription(),dto.getPackSize(),dto.getUnitPrice(),
                dto.getQtyOnHand()));
    }
}
