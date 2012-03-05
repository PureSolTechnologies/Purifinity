/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.config;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This object stores all customer information like names, contacts and logos
 * for later use in classes with customization and copyright, contact and
 * customer information.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CustomerInformation {

    private static final Logger logger = LoggerFactory
	    .getLogger(CustomerInformation.class);

    private static final String CUSTOMER_CONFIG_FILE = "/config/customer";

    /**
     * This method returns the set customers short name.
     * 
     * @return A String containing the customers short name is returned.
     */
    public static String getShortName() {
	return Configurator.getEntry(CUSTOMER_CONFIG_FILE, "GENERAL",
		"shortname", true);
    }

    /**
     * This method returns the set customers long name.
     * 
     * @return A String containing the customers long name is returned.
     */
    public static String getLongName() {
	return Configurator.getEntry(CUSTOMER_CONFIG_FILE, "GENERAL", "name",
		true);
    }

    static public String getCustomerInformation() {
	try {
	    InputStream inStream = CustomerInformation.class
		    .getResourceAsStream(CUSTOMER_CONFIG_FILE);
	    if (inStream == null) {
		return ConfigFile.readSection(CUSTOMER_CONFIG_FILE, "ABOUT");
	    }
	    return ConfigFile.readSection(inStream, "ABOUT");
	} catch (IOException e) {
	    logger.warn("Resource '" + CUSTOMER_CONFIG_FILE
		    + "' was not found!");
	    return "";
	}
    }
}
