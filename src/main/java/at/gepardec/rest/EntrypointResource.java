package at.gepardec.rest;

import at.gepardec.service.MiddlemanService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/call")
@ApplicationScoped
public class EntrypointResource {

    @Inject
    Logger Log;

    int count = 0;

    @Inject
    @RestClient
    MiddlemanService middlemanService;


    @GET
    @Path("/service")
    @Produces(MediaType.TEXT_PLAIN)
    public Response callNextService(@QueryParam("ttl") int ttl) {
        Log.info("Service 2 requesting call of next Service #" + ++count);
        Log.info("ttl_service2: " + ttl);
        return middlemanService.getNextResource(--ttl);
    }

}
