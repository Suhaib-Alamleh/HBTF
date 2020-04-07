package com.suhaib.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

import com.suhaib.entities.User;
import com.suhaib.repository.UserRepository;
import com.suhaib.util.DBConnection;



public class UserRepositoryImpl implements UserRepository {
	
	private static int workload = 12;
	

	@Override
	public User signup(User user) {
		
		String query  = "INSERT INTO user(firstname, lastname, username, password, age, address) "
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		
		try (
			 Connection conn = DBConnection.getInstance().connect();
			 PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		){	
			stmt.setString(1, user.getFirstname());	
			stmt.setString(2, user.getLastname());	
			stmt.setString(3, user.getUsername());	
			stmt.setString(4, hashPassword(user.getPassword()));	
			stmt.setInt(5, user.getAge());	
			stmt.setString(6, user.getAddress());	
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next())
                user.setUserId(rs.getInt(1));
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public User signin(String username, String password) {

		User user = null;
		
		String query = "SELECT id, firstname, lastname, username, password, age, address "
				+ "FROM user WHERE username= ? ";
		
		try (
				 Connection conn = DBConnection.getInstance().connect();
				 PreparedStatement stmt = conn.prepareStatement(query);
			){	
				stmt.setString(1, username);	

				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()) {
					user = new User();
					user.setUserId(rs.getInt("id"));
					user.setFirstname(rs.getString("firstname"));
					user.setLastname(rs.getString("lastname"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setAge(rs.getInt("age"));
					user.setAddress(rs.getString("address"));
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		// check if password matched
		if(user != null) {
			if(!checkPassword(password, user.getPassword())) {
				user = null;
			}
		}
		
		return user;
	}
	
	
	public static String hashPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return(hashed_password);
	}

	
	public static boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return(password_verified);
	}

}
