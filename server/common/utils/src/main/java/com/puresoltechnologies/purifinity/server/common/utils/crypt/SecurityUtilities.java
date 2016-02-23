package com.puresoltechnologies.purifinity.server.common.utils.crypt;

import java.security.Provider;
import java.security.Security;
import java.util.HashSet;
import java.util.Set;

/**
 * This class provides helper functions for the Java Security Framework.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SecurityUtilities {

    /**
     * This method returns all available services types which are currently
     * registered within the security framework.
     * 
     * Found at: http://www.exampledepot.com/egs/java.security/ListServices.html
     * 
     * @return A {@link Set} is returned with the defined service types.
     */
    public static Set<String> getServiceTypes() {
	Set<String> serviceTypes = new HashSet<String>();

	// All all providers
	Provider[] providers = Security.getProviders();
	for (Provider provider : providers) {
	    // Get services provided by each provider
	    for (Object key : provider.keySet()) {
		String keyString = key.toString().split(" ")[0];
		if (keyString.startsWith("Alg.Alias.")) {
		    // Strip the alias
		    keyString = keyString.substring(10);
		}
		int ix = keyString.indexOf('.');
		serviceTypes.add(keyString.substring(0, ix));
	    }
	}
	return serviceTypes;
    }

    /**
     * This method returns all available services implementations of a certain
     * type which are currently registered within the security framework.
     * 
     * Found at: http://www.exampledepot.com/egs/java.security/ListServices.html
     * 
     * @param serviceType
     *            is the type of the implementation for which the
     *            implementations are to be retrieved.
     * @return A {@link Set} of {@link String} is returned with the implemented
     *         services.
     */
    public static Set<String> getServiceImplementations(String serviceType) {
	Set<String> cryptographyImplementations = new HashSet<String>();
	// All all providers
	Provider[] providers = Security.getProviders();
	for (int i = 0; i < providers.length; i++) {
	    // Get services provided by each provider
	    Set<Object> keys = providers[i].keySet();
	    for (Object key : keys) {
		String keyString = key.toString().split(" ")[0];

		if (keyString.startsWith(serviceType + ".")) {
		    cryptographyImplementations.add(keyString.substring(serviceType.length() + 1));
		} else if (keyString.startsWith("Alg.Alias." + serviceType + ".")) {
		    // This is an alias
		    cryptographyImplementations.add(keyString.substring(serviceType.length() + 11));
		}
	    }
	}
	return cryptographyImplementations;
    }

}
