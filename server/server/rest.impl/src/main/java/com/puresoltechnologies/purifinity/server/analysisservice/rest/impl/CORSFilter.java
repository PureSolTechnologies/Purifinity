package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * This filter adds CORS
 * (http://en.wikipedia.org/wiki/Cross-origin_resource_sharing) information to
 * the header. This header information are need to let Angular JS and other
 * services access this REST interfaces from other domains and ports.
 * 
 * @see StackOverflow:
 *      http://stackoverflow.com/questions/23450494/how-to-enable-cross-domain
 *      -requests -on-jax-rs-web-services
 * @author Rick-Rainer Ludwig
 *
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
	    ContainerResponseContext responseContext) throws IOException {
	MultivaluedMap<String, Object> headers = responseContext.getHeaders();
	// Allow from all origins...
	headers.add("Access-Control-Allow-Origin", "*");
	// Allow changing the following headers...
	headers.add("Access-Control-Allow-Headers",
		"origin, content-type, accept, authorization");
	// Allow credentials...
	headers.add("Access-Control-Allow-Credentials", "true");
	// Allow methods...
	headers.add("Access-Control-Allow-Methods",
		"GET, POST, PUT, DELETE, OPTIONS, HEAD");
	// Max 14 days...
	headers.add("Access-Control-Max-Age", "1209600");
    }

}