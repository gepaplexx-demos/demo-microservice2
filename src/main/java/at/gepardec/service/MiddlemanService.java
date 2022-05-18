package at.gepardec.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@RegisterRestClient
public interface MiddlemanService {

    @GET
    @Path("/call/service")
    @Produces(MediaType.TEXT_PLAIN)
    void getNextResource(@QueryParam("ttl") int ttl,
                         @QueryParam("transactionID") UUID transactionID);
}
