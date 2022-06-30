package at.gepardec.service;

import at.gepardec.logging.UuidPropagationClientHeadersFactory;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RegisterRestClient
@RegisterClientHeaders(UuidPropagationClientHeadersFactory.class)
public interface MiddlemanService {

    @GET
    @Path("/call/service")
    @Produces(MediaType.TEXT_PLAIN)
    void getNextResource(@QueryParam("ttl") int ttl);

    @GET
    @Path("/call/serviceBySequence")
    @Produces(MediaType.TEXT_PLAIN)
    void getNextResourceBySequence(@QueryParam("sequence") String sequence);
}
