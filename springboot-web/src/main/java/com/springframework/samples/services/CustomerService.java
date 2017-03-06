package com.springframework.samples.services;

import com.springframework.samples.models.Customer;

public interface CustomerService {
	Iterable<Customer> listAllCustomers();

    Customer getCustomerById(Integer id);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Integer id);
}
