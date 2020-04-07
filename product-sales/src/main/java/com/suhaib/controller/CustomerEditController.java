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
@WebServlet("/secure/customers/edit")
public class CustomerEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CustomerRepository customerRepository = new CustomerRepositoryImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerEditController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String customerId = request.getParameter("customerId");
			
			if(customerId != null) {
				
				Customer customer = customerRepository.get(Integer.parseInt(customerId));
							
				request.setAttribute("customer", customer);
				request.getRequestDispatcher("/views/customers/edit-customer.jsp")
				   .forward(request, response);
			} else {
										
				request.setAttribute("error", "Not found!");
				request.getRequestDispatcher("secure/customers")
				.forward(request, response);
			}
			
		} catch (Exception e) {
			request.getRequestDispatcher("/views/error/error.jsp").forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String customerId = request.getParameter("customerId");
			
			Customer customer = new Customer();
			if(customerId != null && !customerId.equals(""))
			customer.setCustomerId(Integer.parseInt(customerId));
			customer.setFirstName(request.getParameter("firstName"));
			customer.setLastName(request.getParameter("lastName"));
			customer.setPhone(request.getParameter("phone"));
			customer.setAddress(request.getParameter("address"));
			customer.setCity(request.getParameter("city"));
			customer.setState(request.getParameter("state"));
			customer.setPoints(Integer.parseInt(request.getParameter("points")));
			customer.setComments(request.getParameter("comments"));
			
			
			if(customerId == null) {

				request.setAttribute("error", "Not found!");
				response.sendRedirect(getServletContext().getContextPath() + "/secure/customers");
			} else {
				customerRepository.update(customer);
				response.sendRedirect(getServletContext().getContextPath() + "/secure/customers");
			}
			
		} catch (Exception e) {
			request.getRequestDispatcher("/views/error/error.jsp").forward(request, response);
		}
	}

}
