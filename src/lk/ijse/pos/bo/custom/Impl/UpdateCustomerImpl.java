package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.UpdateCustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;

public class UpdateCustomerImpl implements UpdateCustomerBO {

    private final CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public boolean editCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCustID(),dto.getCustTitle(),dto.getCustName(),
                dto.getCustAddress(),dto.getCity(),dto.getProvince(),dto.getPostCode()));
    }

    @Override
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }
}
