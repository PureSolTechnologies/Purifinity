package com.puresoltechnologies.purifinity.server.rest.impl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Defines the REST application for Purifintiy.
 */
@ApplicationPath("/")
public class RestApplication extends Application {

	// @Override
	// public Set<Class<?>> getClasses() {
	// Set<Class<?>> classes = super.getClasses();
	// classes.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
	// classes.add(com.wordnik.swagger.jaxrs.listing.ApiListingCache.class);
	// classes.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
	// classes.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
	// classes.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
	// return classes;
	// }
	//
	// public RestApplication() {
	// BeanConfig config = new BeanConfig();
	// config.setVersion(BuildInformation.getVersion().toString());
	// config.setBasePath("http://localhost:8080/purifinityserver/rest");
	// config.setResourcePackage("io.swagger.resources");
	// config.setScan(true);
	// }
}
