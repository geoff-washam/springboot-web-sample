package com.springframework.samples.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.springframework.samples.models.Customer;
import com.springframework.samples.repositories.*;


public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	
	  @Autowired
	    public void setCustomerRepository(CustomerRepository customerRepository) {
	        this.customerRepository = customerRepository;
	    }
	@Override
	public Iterable<Customer> listAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Integer id) {
		// TODO Auto-generated method stub
		return customerRepository.findOne(id);
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Integer id) {
		customerRepository.delete(id);

	}

}
