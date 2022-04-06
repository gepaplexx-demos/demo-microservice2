package at.gepardec.rest;

import io.quarkus.vertx.web.Route;
import io.vertx.ext.web.RoutingContext;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.mvc.RedirectScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;

@RedirectScoped
@Path("/")
public class HealthResource {

    @Inject
    Logger Log;

    @GET
    @Path("/health")
    public Response getHealth() {
        return Response.seeOther(URI.create("/q/health")).build();
    }
}
