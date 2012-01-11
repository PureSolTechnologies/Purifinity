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

/**
 * This object contains API constants and configuration settings used for API
 * wide settings and behavior storage.
 * 
 * @author Rick-Rainer Ludwig
 */
public class APIInformation {

	/**
	 * This is the version of the whole API package. The idea is:
	 * major.minor.fix<br/>
	 * 
	 * major = change if compatibility is not granted or a major update was
	 * released
	 * 
	 * minor = update and functional enhancement and improvement, but
	 * compatibility is granted
	 * 
	 * fix = bug fix release with no functional changes, but bugs are fixed
	 */
	public static String getPackageVersion() {
		return Configurator.getEntry("/config/about", "GENERAL", "version",
				true);
	}

	/**
	 * This is the constants containing the year range of development. The
	 * starting point was January 2009.
	 */
	public static String getPackageYears() {
		return Configurator.getEntry("/config/about", "GENERAL", "years", true);
	}

	/**
	 * These are all developers which were working on this release as architects
	 * and leaders. The number of programmers would exceed this variable very
	 * fast.
	 */
	public static String getPackageAuthor() {
		return Configurator
				.getEntry("/config/about", "GENERAL", "author", true);
	}

	/**
	 * This is the email address used for bug reports. This is shown to
	 * customers to have an email contact.
	 */
	public static String getPackageBugReport() {
		return Configurator.getEntry("/config/about", "GENERAL", "bugreport",
				true);
	}

	/**
	 * This is the copyright message for about boxes.
	 */
	public static String getPackageCopyright() {
		return Configurator.getEntry("/config/about", "GENERAL", "copyright",
				true);
	}

	/**
	 * This is the legal owner of the software package.
	 */
	public static String getPackageOwner() {
		return Configurator.getEntry("/config/about", "GENERAL", "owner", true);
	}

	/**
	 * This variable is for global usage and is used to keep the help request
	 * information. This variable is set by RTAParser for instance.
	 */
	private static int help = 0;

	/**
	 * This variable is for global usage and is used to keep the version request
	 * information. This variable is set by RTAParser for instance.
	 */
	private static int version = 0;

	/**
	 * This method sets the help request flag of the API.
	 * 
	 * @param helpRequest
	 *            is the value to set for the help request flag. 0 means no help
	 *            screen is requested. Otherwise a help screen is requested and
	 *            to show in the console or a special window.
	 * @throws IllegalArgumentException
	 *             is thrown if helpRequest is less than zero.
	 */
	public static void setHelpRequest(int helpRequest)
			throws IllegalArgumentException {
		if (helpRequest < 0) {
			throw new IllegalArgumentException(
					"helpRequest is alwayse greater than or equal to zero!");
		}
		help = helpRequest;
	}

	/**
	 * This method returns the currently set help request flag.
	 * 
	 * @return The currentely set help request flag is returned. 0 means no help
	 *         screen is requested. Otherwise a help screen is requested and to
	 *         show in the console or a special window.
	 */
	public static int getHelpRequest() {
		return help;
	}

	/**
	 * This method sets the version request flag of the API.
	 * 
	 * @param versionRequest
	 *            is the value to set for the version request flag. 0 means no
	 *            version screen is requested. Otherwise a version screen is
	 *            requested and to show in the console or a special window.
	 * @throws IllegalArgumentException
	 *             is thrown if verionRequest is less than zero.
	 */
	public static void setVersionRequest(int versionRequest)
			throws IllegalArgumentException {
		if (versionRequest < 0) {
			throw new IllegalArgumentException(
					"versionRequest is alwayse greater than or equal to zero!");
		}
		version = versionRequest;
	}

	/**
	 * This method returns the currently set version request flag.
	 * 
	 * @return The currently set version request flag is returned. 0 means no
	 *         version screen is requested. Otherwise a version screen is
	 *         requested and to show in the console or a special window.
	 */
	public static int getVersionRequest() {
		return version;
	}

}
