package at.gepardec.service;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.net.URISyntaxException;

@RequestScoped
public class PingService {

    @Inject
    @RestClient
    private RestClientAPI api;

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public String getResponse() {

        if( api != null ) {
            return api.get();
        }
        return null;
    }


}
