package at.gepardec.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "http://localhost:8080/")
public interface MiddlemanService {

    @GET
    @Path("/trace")
    @Produces(MediaType.TEXT_PLAIN)
    String getNextResource();

}
