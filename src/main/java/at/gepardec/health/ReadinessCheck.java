package at.gepardec.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.sun.management.OperatingSystemMXBean;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.jboss.logging.Logger;

import java.lang.management.ManagementFactory;


@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

    @Inject
    Logger Log;

    OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

    @Override
    public HealthCheckResponse call() {
        Log.info("Calling Readiness-Check: ");
        Log.info("Test");
        Log.info("CPu-Usage: " + osBean.getProcessCpuLoad());
        Log.info("CPu-Usage_System: " + osBean.getSystemLoadAverage());
        return HealthCheckResponse.up("Service2");
    }
}
