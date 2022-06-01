package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.entity.orderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean add(orderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `orderDetail` VALUES(?,?,?,?,?)",orderDetails.getOrderId(),
                orderDetails.getItemCode(),orderDetails.getOrderQty(),orderDetails.getDiscount(),
                orderDetails.getTotal());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM `orderDetail` WHERE OrderID=?",s);
    }

    @Override
    public boolean update(orderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE `orderDetail` SET  orderQTY=?, Discount=?,Price=? WHERE OrderID=?",
                orderDetails.getOrderQty(),orderDetails.getDiscount(),
                orderDetails.getTotal(),orderDetails.getOrderId());
    }

    @Override
    public orderDetails search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM `orderDetail` WHERE OrderID=?",s);
        rst.next();
        return  new orderDetails(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4),rst.getDouble(5));
    }

    @Override
    public ArrayList<orderDetails> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM `orderDetail`");
        ArrayList<orderDetails>getAllOrderDetails=new ArrayList<>();
        while (rst.next()){
            getAllOrderDetails.add(new orderDetails(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4),
                    rst.getDouble(5)));
        }
        return getAllOrderDetails;
    }

    @Override
    public ResultSet getOrderIncomeItems() throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT * FROM `orderDetail`");
    }

    @Override
    public ResultSet getOrderItems(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT  * FROM `orderDetail` WHERE ItemCode=?",id);
    }
}
