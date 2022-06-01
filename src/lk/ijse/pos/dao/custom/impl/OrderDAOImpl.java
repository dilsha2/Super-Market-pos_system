package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.entity.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(order order) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `orders` VALUES (?,?,?)",order.getOrderId(),order.getDate(),
                order.getCustId());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM `orders` WHERE OrderId=?",s);
    }

    @Override
    public boolean update(order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public order search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM `orders` WHERE OrderId=?",s);
        rst.next();
        return new order(rst.getString(1),rst.getString(2),rst.getString(3));
    }

    @Override
    public ArrayList<order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM `orders`");
        ArrayList<order> getAllOrder=new ArrayList<>();

        while (rst.next()){
            getAllOrder.add(new order(rst.getString(1),rst.getString(2),rst.getString(3)));
        }

        return getAllOrder;
    }

    @Override
    public ResultSet getCustomerOrder(String Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("Select * from `orders` WHERE CustID=?",Id);
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT OrderID FROM `orders` ORDER BY OrderId DESC LIMIT 1;");
        return rst.next() ? String.format("O%03d", (Integer.parseInt(rst.getString("OrderId").replace("O", "")) + 1)) : "O001";
    }
}
