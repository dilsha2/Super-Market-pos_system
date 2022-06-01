package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.AddNewCustomerBO;
import lk.ijse.pos.bo.superBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;

public class AddNewCustomerImpl implements AddNewCustomerBO {
    private final CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public boolean addNewCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(dto.getCustID(),dto.getCustTitle(),dto.getCustName(),dto.getCustAddress()
                ,dto.getCity(),dto.getProvince(),dto.getPostCode()));
    }
}
