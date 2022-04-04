package at.gepardec.rest;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.mvc.RedirectScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/")
@RedirectScoped
public class PingResource {

    @Inject
    Logger Log;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.seeOther(URI.create("localhost:8080/loadtesting/cpu/12/5")).build();
    }
}
