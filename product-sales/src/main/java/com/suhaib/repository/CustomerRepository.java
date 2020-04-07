package com.suhaib.repository;

import java.sql.SQLException;
import java.util.List;

import com.suhaib.entities.Customer;


public interface CustomerRepository {

	public Customer create(Customer customer) throws Exception;
	
	public Customer update(Customer customer) throws Exception;
	
	public boolean delete(int customerId) throws Exception;
	
	public Customer get(int customerId) throws Exception;
	
	public List<Customer> list() throws Exception;
	
}
