package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.superBO;
import lk.ijse.pos.dto.ItemDTO;

import java.sql.SQLException;

public interface AddNewItemBO extends superBO {
    public boolean addItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
}
