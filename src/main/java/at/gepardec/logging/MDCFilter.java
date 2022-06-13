package at.gepardec.logging;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Priority(Priorities.USER - 500)
@Provider
public class MDCFilter implements ContainerRequestFilter, ContainerResponseFilter {

    protected static final String HEADER_ORDER_UUID = "X-ORDER-UUID";

    protected static final String HEADER_BUSINESS_ID = "X-BUSINESS-ID";

    protected static final String LOG_ORDER_UUID = "order.uuid";

    protected static final String LOG_BUSINESS_ID = "business.id";

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        putIfNotEmpty(requestContext, HEADER_ORDER_UUID, LOG_ORDER_UUID);
        putIfNotEmpty(requestContext, HEADER_BUSINESS_ID, LOG_BUSINESS_ID);
    }

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) throws IOException {
        MDC.remove(LOG_ORDER_UUID);
        MDC.remove(LOG_BUSINESS_ID);
    }

    private void putIfNotEmpty(final ContainerRequestContext requestContext, final String headerKey, final String logKey) {
        final String headerValue = requestContext.getHeaders().getFirst(headerKey);
        if (StringUtils.isNotEmpty(headerValue)) {
            MDC.put(logKey, headerValue);
        }
    }
}
