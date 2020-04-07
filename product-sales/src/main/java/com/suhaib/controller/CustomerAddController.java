package com.suhaib.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suhaib.entities.Customer;
import com.suhaib.repository.CustomerRepository;
import com.suhaib.repository.impl.CustomerRepositoryImpl;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/secure/customers/add")
public class CustomerAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CustomerRepository customerRepository = new CustomerRepositoryImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerAddController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/views/customers/add-customer.jsp")
			.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Customer customer = new Customer();
			customer.setFirstName(request.getParameter("firstName"));
			customer.setLastName(request.getParameter("lastName"));
			customer.setPhone(request.getParameter("phone"));
			customer.setAddress(request.getParameter("address"));
			customer.setCity(request.getParameter("city"));
			customer.setState(request.getParameter("state"));
			customer.setPoints(Integer.parseInt(request.getParameter("points")));
			customer.setComments(request.getParameter("comments"));
			
			customerRepository.create(customer);
			response.sendRedirect(getServletContext().getContextPath() + "/secure/customers");
			
		} catch (Exception e) {
			request.getRequestDispatcher("/views/error/error.jsp").forward(request, response);
		}
		
	}

}
