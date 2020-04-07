package com.suhaib.api.filters;

import java.io.IOException;
import java.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import com.suhaib.entities.User;
import com.suhaib.repository.UserRepository;
import com.suhaib.repository.impl.UserRepositoryImpl;

@Provider
public class ApiAuthFilter implements ContainerRequestFilter {
	
	private static final String AUTH_HEADER_NAME = "Authorization";
	private static final String AUTH_HEADER_PREFIX = "Basic ";
	
	UserRepository userRepository = new UserRepositoryImpl();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		Response response = Response
				.status(Response.Status.UNAUTHORIZED)
				.entity("Un authorized user.")
				.build();
		
		try {
			
			UriInfo info = requestContext.getUriInfo();
		    if (!info.getPath().contains("customers")) {
		        return;
		    }
			
			String auth = requestContext.getHeaderString(AUTH_HEADER_NAME);
			
			if(auth != null) {
				auth = auth.replace(AUTH_HEADER_PREFIX, "");
			}
			
			byte[] authdata = Base64.getDecoder().decode(auth);
			
			String userCrd = new String(authdata);
			
			String[] tokens = userCrd.split(":");
			
			String username = tokens[0];
			String password = tokens[1];
			
			User user = userRepository.signin(username, password);
			
			if(user != null ) {
				return; 
			}
			
		} catch (Exception e) {
			requestContext.abortWith(response);
		}
		
		requestContext.abortWith(response);
	}
	
	
	

}
