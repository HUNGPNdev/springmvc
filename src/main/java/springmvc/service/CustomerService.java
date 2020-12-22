package springmvc.service;

import java.util.List;

import springmvc.entity.Customer;

public interface CustomerService {

    List <Customer> getCustomers();

    void saveCustomer(Customer theCustomer);

    Customer getCustomer(int theId);

    void deleteCustomer(int theId);

}
