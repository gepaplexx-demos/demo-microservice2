package at.gepardec.rest;

import at.gepardec.service.PingService;
import at.gepardec.service.RestClientAPI;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.RedirectScoped;
import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@RequestScoped
@Path("/")
public class PingResource {

    @Inject
    Logger Log;

    @Inject
    private PingService ps;


    @Controller
    @Path("/ping")
    @GET
    public String ping() {
        Log.info(ps.getResponse());
        return ps.getResponse();
    }
}
