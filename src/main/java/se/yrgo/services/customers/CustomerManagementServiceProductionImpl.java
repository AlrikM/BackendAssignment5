package se.yrgo.services.customers;

import se.yrgo.dataaccess.CustomerDao;
import se.yrgo.dataaccess.RecordNotFoundException;
import se.yrgo.domain.Call;
import se.yrgo.domain.Customer;

import java.util.List;

public class CustomerManagementServiceProductionImpl implements CustomerManagementService {
    private CustomerDao customerDao;
    public CustomerManagementServiceProductionImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    @Override
    public void newCustomer(Customer newCustomer){
        customerDao.create(newCustomer);
    }

    @Override
    public void updateCustomer(Customer newCustomer){
        try{
            customerDao.update(newCustomer);
        }catch (RecordNotFoundException e){
            System.out.println("Record not found");
        }
    }
    @Override
    public void deleteCustomer(Customer newCustomer){
        try{
            customerDao.delete(newCustomer);
        }catch (RecordNotFoundException e){
            System.out.println("Record not found");
        }
    }
    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
        try{
            return customerDao.getById(customerId);
        }catch(RecordNotFoundException e){
            System.err.println("Unable to find customer ID at " + e);
            return null;
        }
    }
    @Override
    public List<Customer> findCustomersByName(String name){
        return customerDao.getByName(name);
    }
    @Override
    public List<Customer> getAllCustomers(){
        return customerDao.getAllCustomers();
    }
    @Override
    public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
        try{
            return customerDao.getFullCustomerDetail(customerId);
        }catch(RecordNotFoundException e){
            System.err.println("Customer could not be found at " + e);
            return null;
        }
    }
    @Override
    public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException{
        try{
            customerDao.addCall(callDetails, customerId);
        }catch(RecordNotFoundException e){
            System.err.println("Failed to add call to customer record at " + e);
        }
    }
}
