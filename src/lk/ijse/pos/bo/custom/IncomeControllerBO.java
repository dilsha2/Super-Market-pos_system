package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.superBO;
import lk.ijse.pos.entity.ItemQtyRates;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IncomeControllerBO extends superBO {
    ResultSet setIncomeData() throws SQLException, ClassNotFoundException;
    List<ItemQtyRates> getItemCode() throws SQLException, ClassNotFoundException;
    int getItemqty(String s) throws SQLException, ClassNotFoundException;
    List<String> getAllItemCode() throws SQLException, ClassNotFoundException;
}
