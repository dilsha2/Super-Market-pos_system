package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.superBO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ModifyItemBO extends superBO {
    boolean addNewItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
    Item getItem(String id) throws SQLException, ClassNotFoundException;
    List<String> loadItemIds() throws SQLException, ClassNotFoundException;
}
