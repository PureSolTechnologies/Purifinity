package com.puresoltechnologies.purifinity.server.common.rest;

import java.io.IOException;
import java.net.URI;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * This is a generic implementation of a REST client provided with RestEasy.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <GenericRestInterface>
 *            is the acutal interface to be provided with the REST annotations
 *            for interface specification.
 */
public class AbstractRestClient<GenericRestInterface> implements AutoCloseable {

    private final ResteasyClient client;
    private final GenericRestInterface proxy;

    static {
	RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
    }

    /**
     * This constructor is used for the actual construction. The needed REST
     * interface class is provided here to bind the client to the correct
     * implementation.
     * 
     * @param restInterface
     *            is the interface class with the specification annotations.
     */
    protected AbstractRestClient(URI uri,
	    Class<GenericRestInterface> restInterface) {
	ResteasyClientBuilder resteasyClientBuilder = new ResteasyClientBuilder();
	resteasyClientBuilder.register(JacksonJsonProvider.class);
	client = resteasyClientBuilder.build();
	ResteasyWebTarget webTarget = client.target(uri);
	proxy = webTarget.proxy(restInterface);
    }

    @Override
    public final void close() throws IOException {
	client.close();
    }

    /**
     * This method returns the REST proxy fo the client.
     * 
     * @return A proxy implementing the REST interface is returned.
     */
    protected final GenericRestInterface getProxy() {
	return proxy;
    }
}
