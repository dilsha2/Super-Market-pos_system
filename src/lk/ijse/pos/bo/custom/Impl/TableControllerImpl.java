package lk.ijse.pos.bo.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.pos.bo.custom.TableControllBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.order;
import lk.ijse.pos.entity.orderDetails;
import lk.ijse.pos.view.TM.CustomerTM;
import lk.ijse.pos.view.TM.ItemTM;
import lk.ijse.pos.view.TM.OrderDetailsTM;
import lk.ijse.pos.view.TM.OrderTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class TableControllerImpl implements TableControllBO {

    private final CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDetailsDAO orderDetailsDAO= (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public ObservableList<CustomerTM> loadAllCustomerTable() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customerTMS=customerDAO.getAll();
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();

        customerTMS.forEach(e->obList.addAll(new CustomerTM(e.getCustId(),e.getCustTitle(),e.getCustName(),
                e.getCustAddress(),e.getCity(),e.getProvince(),e.getPostalCode())));
        return obList;
    }

    @Override
    public ObservableList<ItemTM> loadAllItemTable() throws SQLException, ClassNotFoundException {
        ArrayList<Item> customerTMS=itemDAO.getAll();
        ObservableList<ItemTM> obList = FXCollections.observableArrayList();

        customerTMS.forEach(e->obList.addAll(new ItemTM(e.getItemCode(),e.getDescription(),e.getPackSize(),
                e.getUnitPrice(),e.getQtyOnHand())));
        return obList;
    }

    @Override
    public ObservableList<OrderTM> loadAllOrderTable() throws SQLException, ClassNotFoundException {
        ArrayList<order> orderTMS=orderDAO.getAll();
        ObservableList<OrderTM> obList = FXCollections.observableArrayList();
        orderTMS.forEach(e->obList.addAll(new OrderTM(e.getOrderId(),e.getDate(),e.getCustId())));
        return obList;
    }

    @Override
    public ObservableList<OrderDetailsTM> loadAllOrderDetailsTable() throws SQLException, ClassNotFoundException {
        ArrayList<orderDetails> orderDetailsTMS=orderDetailsDAO.getAll();
        ObservableList<OrderDetailsTM> obList = FXCollections.observableArrayList();
        orderDetailsTMS.forEach(e->obList.addAll(new OrderDetailsTM(e.getOrderId(),e.getItemCode(),e.getOrderQty(),
                e.getDiscount(),e.getTotal())));
        return obList;
    }
}
