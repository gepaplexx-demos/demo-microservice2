package at.gepardec.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Random;

@Dependent
public class RandomCallService {

    @Inject
    Logger Log;

    private int countServices;

    Random random;
    Long seed;

    ServiceCollector serviceCollection;

    public RandomCallService(ServiceCollector serviceCollection,
                             @ConfigProperty(name = "microservices.seed") Long seed) {
        this.serviceCollection = serviceCollection;
        this.seed = seed;
        this.countServices = serviceCollection.getServiceURLs().size();
        random = new Random(seed);
    }

    public void callRandomService(int ttl) {
        getRandomService().getNextResource(ttl);
    }

    public MiddlemanService getRandomService() {
        String url = getRandomUrl();
        Log.info("Service: " + url);
        return RestClientBuilder
                .newBuilder()
                .baseUri(URI.create(url))
                .build(MiddlemanService.class);
    }

    public String getRandomUrl() {
        return serviceCollection.getServiceURLs().get(getRandomNr());
    }

    public int getRandomNr() {
        return random.nextInt(countServices);                                           // returns values of interval [0 ; #services-1]
    }


}