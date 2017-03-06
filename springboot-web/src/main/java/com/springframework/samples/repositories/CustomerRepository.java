package com.springframework.samples.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springframework.samples.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{
}
