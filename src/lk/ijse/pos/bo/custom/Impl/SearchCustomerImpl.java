package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.SearchCustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;

public class SearchCustomerImpl implements SearchCustomerBO {
    private final CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }
}
