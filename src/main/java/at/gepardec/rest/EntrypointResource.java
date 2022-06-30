package at.gepardec.rest;

import at.gepardec.service.OrderedCallService;
import at.gepardec.service.ServiceCollector;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/call")
@ApplicationScoped
public class EntrypointResource {

    @Inject
    Logger Log;

    @Inject
    ServiceCollector serviceCollector;

    @GET
    @Path("/serviceBySequence")
    @Counted(name = "performedCalls", description = "How often the service has been called.")
    @Timed(name = "callsTimer", description = "A measure of how long it takes to perform the complete call.", unit = MetricUnits.MILLISECONDS)
    @Produces(MediaType.TEXT_PLAIN)
    public void callNextService(@QueryParam("sequence") String sequence) {
        processRequest(sequence);
    }

    public void processRequest(String orderSequence) {
        Log.info("Sequence: " + orderSequence);
        OrderedCallService orderedCallService = new OrderedCallService(serviceCollector.getServiceURLs());
        switch (orderSequence) {
            case "":                                                                                // empty sequence => Stop CAllservice and
                orderedCallService.sendStopNotifications();                            // send stop notifications to all other services
                return;
            case "-":                                                                               // received stop notification
                Log.info("Stopping OrderedCallService...\n\n");
                return;
        }
        Log.info("Calling next Service");
        orderedCallService.callServiceBySequence(orderSequence);
    }
}
