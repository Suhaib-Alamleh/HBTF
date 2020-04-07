package com.suhaib.api.filters;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
 
@Provider
public class ApiException extends Exception implements
                ExceptionMapper<ApiException> 
{
    private static final long serialVersionUID = 1L;
 
    public ApiException() {
        super("No File found with given name !!");
    }
 
    public ApiException(String string) {
        super(string);
    }
 
    @Override
    public Response toResponse(ApiException exception) 
    {
        return Response.status(500).entity(exception.getMessage())
                                    .type("text/plain").build();
    }
}