package com.suhaib.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.suhaib.entities.User;
import com.suhaib.repository.UserRepository;
import com.suhaib.repository.impl.UserRepositoryImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns={"/auth"})
public class AuthController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	UserRepository userRepository = new UserRepositoryImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthController() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		out.println("Logged out successfully wait for your next session ");
		session.invalidate();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		if(request.getParameter("auth").equals("signup")) {
			
			User user = new User();
			user.setFirstname(request.getParameter("firstname"));
			user.setLastname(request.getParameter("lastname"));
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setAge(Integer.parseInt(request.getParameter("age")));
			user.setAddress(request.getParameter("address"));
			
			userRepository.signup(user);
			
			request.setAttribute("message", "You are registered successfully");
			request.getRequestDispatcher("views/auth/login.jsp").include(request, response);
			
		} else if(request.getParameter("auth").equals("signin")) {
			
			HttpSession session = request.getSession();
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			User user = userRepository.signin(username, password);
			
			if(user == null){
				request.setAttribute("message", "Invalid username or password");
				request.getRequestDispatcher("views/auth/login.jsp").include(request, response);
			} else {
				session.setAttribute("user", user);
				response.sendRedirect("secure/customers");
			}
		}
				
		
	}

}
