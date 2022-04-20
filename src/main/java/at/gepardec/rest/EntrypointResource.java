package at.gepardec.rest;

import at.gepardec.service.MiddlemanService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/call")
public class EntrypointResource {

    @Inject
    Logger Log;

    @Inject
    @RestClient
    MiddlemanService middlemanService;

    @GET
    @Path("/service")
    @Produces(MediaType.TEXT_PLAIN)
    public String callNextService() {
        Log.info("Service 2 requesting call of next Service...");
        return middlemanService.getNextResource();
    }

}
