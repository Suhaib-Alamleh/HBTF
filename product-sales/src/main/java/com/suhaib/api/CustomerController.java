package com.suhaib.api;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.suhaib.api.filters.ApiException;
import com.suhaib.entities.Customer;
import com.suhaib.repository.CustomerRepository;
import com.suhaib.repository.impl.CustomerRepositoryImpl;

@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {
	
	CustomerRepository customerRepository = new CustomerRepositoryImpl();

	@GET
	public List<Customer> list() throws Exception {
		List<Customer> list = null;
		
		try {
			list = customerRepository.list();
		} catch (Exception e) {
			throw new ApiException(e.getMessage());
		}
		
		return list;
	}

	@POST
	public Customer create(Customer customer) throws Exception {
		
		Customer createdCustomer = null;
		
		try {
			createdCustomer = customerRepository.create(customer);
		} catch (Exception e) {
			throw new ApiException(e.getMessage());
		}
		
		return createdCustomer;
	}

	@PUT
	public Customer update(Customer customer) throws Exception {
		
		Customer updatedCustomer = null;
		
		try {
			updatedCustomer = customerRepository.update(customer);
		} catch (Exception e) {
			throw new ApiException(e.getMessage());
		}
		
		return updatedCustomer;
	}

	@DELETE
	@Path("/{customerId}")
	public boolean delete(@PathParam("customerId") int customerId) throws Exception {
		
		boolean delete = false;
		
		try {
			delete = customerRepository.delete(customerId);
		} catch (Exception e) {
			throw new ApiException(e.getMessage());
		}
		
		return delete;
	}

	@GET
	@Path("/{customerId}")
	public Customer get(@PathParam("customerId") int customerId) throws Exception {
		
		Customer customer = null;
		
		try {
			customer = customerRepository.get(customerId);
		} catch (Exception e) {
			throw new ApiException(e.getMessage());
		}
		
		return customer;
	}

}
