package lk.ijse.pos.bo.custom.Impl;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.pos.DB.DBConnection;
import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.order;
import lk.ijse.pos.entity.orderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MakeOrderImpl implements OrderBO {
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final CustomerDAO customerDAO=
            (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDetailsDAO orderDetailsDAO= (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewOrderId();
    }

    @Override
    public boolean placeOrder(OrderDTO dto) {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            if (orderDAO.add(new order(dto.getOrderID(),dto.getOrderDate(),dto.getCustID()))) {
                for (OrderDetailsDTO temp : dto.getItems()) {
                    orderDetails orderDetails = new orderDetails(dto.getOrderID(), temp.getItemCode(),
                            temp.getOrderQTY(), temp.getDiscont(), temp.getTotal());
                    if (orderDetailsDAO.add(orderDetails)) {
                        con.commit();
                        return true;
                    } else {
                        con.rollback();
                        return false;
                    }
                }
            } else {
                con.rollback();
                return false;
            }
        }catch (SQLException | ClassNotFoundException throwables){
            throwables.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<String> loadItemIds() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemIds();
    }

    @Override
    public Item setItemData(String itemId) throws SQLException, ClassNotFoundException {
        return itemDAO.search(itemId);
    }

    @Override
    public void updateItemQTY(String id, int qty) throws SQLException, ClassNotFoundException {
        itemDAO.updateQty(id,qty);
    }
    @Override
    public Customer setCustomerData(String customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.search(customerId);
    }
    @Override
    public List<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerIds();
    }
}
