package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?,?)",item.getItemCode(),item.getDescription(),
                item.getPackSize(),item.getUnitPrice(),item.getQtyOnHand());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Item WHERE ItemCode=?",id);
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET Description=?, " +
                        "PackSize=?, UnitPrice=?,QtyOnHand=? WHERE ItemCode=?",item.getDescription(),
                item.getPackSize(),item.getUnitPrice(),item.getQtyOnHand(),item.getItemCode());
    }

    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM Item WHERE ItemCode=?",s);
        rst.next();
        return new Item(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4),rst.getInt(5));
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM Item");
        ArrayList<Item> getAllItem=new ArrayList<>();

        while (rst.next()){
            getAllItem.add(new Item(rst.getString(1), rst.getString(2),rst.getString(3),rst.getDouble(4),rst.getInt(5)));
        }
        return getAllItem;
    }

    @Override
    public ResultSet getCustomerOrderItem(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT * From `orderDetail` WHERE OrderID=?",id);
    }

    @Override
    public List<String> getItemIds() throws SQLException, ClassNotFoundException {
        List <String> ids=new ArrayList<>();
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM Item");
        while (rst.next()){
            ids.add(rst.getString(1));

        }
        return ids;
    }

    @Override
    public void updateQty(String id, int qty) throws SQLException, ClassNotFoundException {
        CrudUtil.executeUpdate("UPDATE Item SET QtyOnHand=(qtyOnHand-"+qty+ ") WHERE ItemCode=?");
    }
}
