package at.gepardec.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import java.util.*;

@Dependent
public class ServiceCollector {

    Logger Log;

    private Map<String, String> serviceURLMap = new HashMap<>();                                    // Map<Env-Variable, URL>
    private List<String> ms_env_variables;

    private String ms_env_base;

    public ServiceCollector(
            @ConfigProperty(name = "microservices.env.base")
                    String base,
            @ConfigProperty(name = "microservices.env.variables")
                    List<String> variables,
            Logger Log) {
        this.ms_env_base = base;
        this.ms_env_variables = variables;
        this.Log = Log;
        initServices();
        logServiceURLs();
    }

    /*
        initialize Map of environment-variables + values
     */
    public void initServices() {
        String env;                                                                                 // environment-variable
        String url;                                                                                 // URL stored in environment-variable
        for (String variable : ms_env_variables) {                                                  // iterate all env-Variables
            env = ms_env_base + variable;
            url = System.getenv(env);                                                               // get value of environment-variable
            serviceURLMap.put(env, url);
        }
    }

    public List<String> getServiceURLs() {
        return new ArrayList<>(serviceURLMap.values());
    }

    public void logServiceURLs() {
        Log.info("ServiceURLs: ");
        for (String s : serviceURLMap.keySet()) {
            Log.info(s + ": " + serviceURLMap.get(s));
        }
    }
}
