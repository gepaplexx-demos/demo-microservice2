package at.gepardec.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.jboss.logging.Logger;

import java.net.URI;
import java.util.List;
import java.util.Random;

public class OrderedCallService {

    Logger Log = Logger.getLogger(OrderedCallService.class);

    String orderSequence;

    List<String> serviceCollection;

    int idletime;

    public OrderedCallService(List<String> serviceCollection, int idletime) {
        this.serviceCollection = serviceCollection;
        this.idletime = idletime;
    }

    public void callServiceBySequence(String orderSequence) {
        this.orderSequence = orderSequence;
        int actionSymbol = parseOrderSequence();
        chooseActionBySymbol(actionSymbol);
    }

    private int parseOrderSequence() {
        int actionSymbol = Integer.parseInt(orderSequence.substring(0, 1));
        orderSequence = orderSequence.substring(1);
        return actionSymbol;
    }

    private void chooseActionBySymbol(int actionSymbol) {
        ServiceAction serviceAction = ServiceAction.values()[actionSymbol];
        switch (serviceAction) {
            case OK1:
                validateForError(0);
                break;
            case ERROR1:
                validateForError(1);
                break;
            case ONEOUTTENERROR1:
                validateForError(10);
                break;
            case ONEOUTHUNDREDERROR1:
                validateForError(100);
                break;
            case DELAY1:
                makeDelay();
                break;

            case OK2:
                validateForError(0);
                break;
            case ERROR2:
                validateForError(1);
                break;
            case ONEOUTTENERROR2:
                validateForError(10);
                break;
            case ONEOUTHUNDREDERROR2:
                validateForError(100);
                break;
            case DELAY2:
                makeDelay();
                break;
            default:
                return;
        }
        callService(actionSymbol / 5);
    }

    private void validateForError(int probability) {
        Random random = new Random();
        if (probability != 0 && random.nextInt(probability) == 0) {
            Log.error("An error occured while trying to call next service");
            return;     // welcher error solls sein? und soll request dann weitergeführt werden?
        }
    }

    private void makeDelay() {
        try {
            Thread.sleep(idletime);
        } catch (InterruptedException e) {
            Log.error("makeDelay was interrupted");
        }
    }

    private void callService(int serviceNr) {
        String url = getUrl(serviceNr);
        Log.info(" Next Service: " + url);
        getService(url).getNextResourceBySequence(orderSequence);
    }

    public MiddlemanService getService(String url) {
        return RestClientBuilder
                .newBuilder()
                .baseUri(URI.create(url))
                .build(MiddlemanService.class);
    }

    public String getUrl(int idx) {
        return serviceCollection.get(idx);
    }

    public void sendStopNotifications() {
        for (String url : serviceCollection) {
            getService(url).getNextResourceBySequence("-");
        }
    }

}