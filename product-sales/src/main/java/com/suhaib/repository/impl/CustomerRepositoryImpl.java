package com.suhaib.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.suhaib.entities.Customer;
import com.suhaib.repository.CustomerRepository;
import com.suhaib.util.DBConnection;



public class CustomerRepositoryImpl implements CustomerRepository {
	

	@Override
	public Customer create(Customer customer) throws SQLException {
		
		String query  = "INSERT INTO customers(first_name, last_name, phone, "
				+ " address, city, state, points, comments) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (
			 Connection conn = DBConnection.getInstance().connect();
			 PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		){	
			stmt.setString(1, customer.getFirstName());	
			stmt.setString(2, customer.getLastName());	
			stmt.setString(3, customer.getPhone());	
			stmt.setString(4, customer.getAddress());	
			stmt.setString(5, customer.getCity());	
			stmt.setString(6, customer.getState());	
			stmt.setInt(7, customer.getPoints());
			stmt.setString(8, customer.getComments());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next())
                customer.setCustomerId(rs.getInt(1));
            
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return customer;
	}

	@Override
	public Customer update(Customer customer) throws SQLException {

		String query  = "UPDATE customers SET first_name= ?, last_name= ?,"
				+ " phone= ?, address= ?, city= ?, state=?, points= ?, comments=?  "
				+ " WHERE customer_id= ?";
		
		try (
			 Connection conn = DBConnection.getInstance().connect();
			 PreparedStatement stmt = conn.prepareStatement(query);
		){	
			stmt.setString(1, customer.getFirstName());	
			stmt.setString(2, customer.getLastName());	
			stmt.setString(3, customer.getPhone());	
			stmt.setString(4, customer.getAddress());	
			stmt.setString(5, customer.getCity());	
			stmt.setString(6, customer.getState());	
			stmt.setInt(7, customer.getPoints());
			stmt.setString(8, customer.getComments());
			stmt.setInt(9, customer.getCustomerId());
			
			stmt.executeUpdate();
            
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return customer;
	}
	
	@Override
	public boolean delete(int customerId) throws SQLException {
		
		boolean deleted = false;
		
		String query  = "DELETE FROM customers WHERE customer_id= ?";
		
		try (
			 Connection conn = DBConnection.getInstance().connect();
			 PreparedStatement stmt = conn.prepareStatement(query);
		){	
			stmt.setInt(1, customerId);	
			deleted = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return deleted;
	}

	@Override
	public Customer get(int customerId) throws SQLException {
		
		Customer customer = new Customer();
		
		String query  = "SELECT c.customer_id, c.first_name, c.last_name, c.phone, "
				+ " c.address, c.city, c.state, c.points, c.comments"
				+ " FROM customers c "
				+ " WHERE c.customer_id = ?";
		
		try (
			 Connection conn = DBConnection.getInstance().connect();
			 PreparedStatement stmt = conn.prepareStatement(query);
		){	
			stmt.setInt(1, customerId);	
			ResultSet rs = stmt.executeQuery();
			
			customer = extractCustomer(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return customer;
	}

	@Override
	public List<Customer> list() throws SQLException {


		StringBuilder query  = new StringBuilder();
		List<Customer> customers = new ArrayList<Customer>();
		
		query.append("SELECT customer_id, first_name, last_name, phone, "
				+ " address, city, state, points, comments FROM customers "
				+ "WHERE 1=1 ");
		
		try (
			 Connection conn = DBConnection.getInstance().connect();
			 PreparedStatement stmt = conn.prepareStatement(query.toString());
		){	
			
			ResultSet rs = stmt.executeQuery();
			
			customers = extractCustomers(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return customers;
	}
	
	private List<Customer> extractCustomers(ResultSet rs) throws SQLException {
		
		List<Customer> customers = new ArrayList<Customer>();
		
		while(rs.next()) {
			Customer customer = new Customer();
			customer.setCustomerId(rs.getInt("customer_id"));
			customer.setFirstName(rs.getString("first_name"));
			customer.setLastName(rs.getString("last_name"));
			customer.setPhone(rs.getString("phone"));
			customer.setAddress(rs.getString("address"));
			customer.setCity(rs.getString("city"));
			customer.setState(rs.getString("state"));
			customer.setPoints(rs.getInt("points"));
			customer.setComments(rs.getString("comments"));
			customers.add(customer);
		}
		
		return customers;
	}
	
	
	private Customer extractCustomer(ResultSet rs) throws SQLException {
		
		Customer customer = new Customer();
		
		if(rs.next()) {
			
			customer.setCustomerId(rs.getInt("c.customer_id"));
			customer.setFirstName(rs.getString("c.first_name"));
			customer.setLastName(rs.getString("c.last_name"));
			customer.setPhone(rs.getString("c.phone"));
			customer.setAddress(rs.getString("c.address"));
			customer.setCity(rs.getString("c.city"));
			customer.setState(rs.getString("c.state"));
			customer.setPoints(rs.getInt("c.points"));
			customer.setComments(rs.getString("c.comments"));
			
		}
		
		return customer;
	}

}
