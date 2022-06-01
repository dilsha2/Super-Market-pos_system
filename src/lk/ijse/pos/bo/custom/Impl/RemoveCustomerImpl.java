package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.RemoveCustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public class RemoveCustomerImpl implements RemoveCustomerBO {

    private final CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public boolean removeCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
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

