package at.gepardec.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.jboss.logging.Logger;

@Liveness
@ApplicationScoped
public class LifenessCheck implements HealthCheck {

    @Inject
    Logger Log;

    @Override
    public HealthCheckResponse call() {
        Log.info("Calling Liveness-Check: ");
        return HealthCheckResponse.up("Service2");
    }
}