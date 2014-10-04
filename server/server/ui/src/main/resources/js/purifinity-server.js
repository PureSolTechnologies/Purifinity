/*
 * This JavaScript file contains connectors and connection logic for Purifinity
 * servers.
 */

/**
 * This function adds functionality to an AngularJS application which is needed
 * to handle Purifinity servers efficiently.
 */
function addPurifinityServerFunctionality(angularApplication) {
	/*
	 * Add baseURL constant as it is read out of the configuration for later use
	 * in modules and controllers.
	 */
	angularApplication.constant("baseURL", "http://" + server.host + ":"
			+ server.port)
}
