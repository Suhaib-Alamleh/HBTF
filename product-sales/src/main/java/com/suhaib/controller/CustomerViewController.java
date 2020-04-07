package com.suhaib.controller;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/secure/customers")
public class CustomerViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CustomerRepository customerRepository = new CustomerRepositoryImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerViewController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			List<Customer> customers = customerRepository.list();
			request.setAttribute("customers", customers);
			request.getRequestDispatcher("/views/customers/list-customers.jsp")
				.forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("/views/error/error.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String customerId = request.getParameter("customerId");
			
			if(customerId == null) {

				request.setAttribute("message", "Not found!");
				response.sendRedirect(getServletContext().getContextPath() + "/secure/customers");
			} else {
				request.setAttribute("message", "Customer with ID " + customerId + " deleted successfully..");
				customerRepository.delete(Integer.parseInt(customerId));
				
				
				response.sendRedirect(getServletContext().getContextPath() + "/secure/customers");
			}
			
		} catch (Exception e) {
			request.getRequestDispatcher("/views/error/error.jsp").forward(request, response);
		}
		
	}

}
