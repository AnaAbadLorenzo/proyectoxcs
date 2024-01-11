package es.uvigo.esei.dgss.teama.microstories.rest.exceptions;

import javax.ejb.EJBAccessException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StoryResourceEJBAccessExceptionMapper implements ExceptionMapper<EJBAccessException> {
  @Override
  public Response toResponse(EJBAccessException e) {
    return Response.status(Response.Status.FORBIDDEN)
        .entity("Illegal access by user. Error: " + e.getMessage())
        .type(MediaType.TEXT_PLAIN)
        .build();
  }
}
