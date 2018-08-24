package com.puresoltechnologies.toolshed.server.impl.filters;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This filter adds CORS
 * (http://en.wikipedia.org/wiki/Cross-origin_resource_sharing) information to
 * the header. This header information are need to let Angular JS and other
 * services access this REST interfaces from other domains and ports.
 * 
 * StackOverflow:
 * http://stackoverflow.com/questions/23450494/how-to-enable-cross-domain
 * -requests -on-jax-rs-web-services
 * 
 * @author Rick-Rainer Ludwig
 *
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

    private static final Logger logger = LoggerFactory.getLogger(CORSFilter.class);

    @PostConstruct
    public void postConstruct() {
	logger.info("CORS headers are enabled.");
    }

    @PreDestroy
    public void preDestroy() {
	logger.info("CORS headers are disabled.");
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
	    throws IOException {
	MultivaluedMap<String, Object> headers = responseContext.getHeaders();
	// Allow from all origins...
	if (!headers.containsKey("Access-Control-Allow-Origin")) {
	    headers.add("Access-Control-Allow-Origin", "*");
	}
	// Allow changing the following headers...
	if (!headers.containsKey("Access-Control-Allow-Headers")) {
	    headers.add("Access-Control-Allow-Headers",
		    "auth-id, auth-token, email, password, new-password, content-type");
	}
	// Allow exposure for following headers....
	if (!headers.containsKey("Access-Control-Expose-Headers")) {
	    headers.add("Access-Control-Expose-Headers", "auth-id, auth-token, email");
	}
	// Allow credentials...
	if (!headers.containsKey("Access-Control-Allow-Credentials")) {
	    headers.add("Access-Control-Allow-Credentials", "true");
	}
	// Allow methods...
	if (!headers.containsKey("Access-Control-Allow-Methods")) {
	    headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	}
	// Max 14 days...
	if (!headers.containsKey("Access-Control-Max-Age")) {
	    headers.add("Access-Control-Max-Age", "1209600");
	}
    }

}