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

import java.util.Vector;

/**
 * RTAParser is an object to handle runtime arguments. This object is
 * implemented in a way that all needed command line switches like --help and
 * --version are implemented and documented automatically.
 * 
 * The object itself contains only methods for reading the command line
 * arguments, but forces to tell during read what the command line argument is
 * useful to. These information are collected in background and can be shown
 * afterwards in a help screen.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RTAParser {

    /**
     * This constant is used for reading stackable command line arguments.
     */
    public static final boolean RTA_STACKABLE = true;

    /**
     * This constant is used by reading non-stackable command line arguments.
     */
    public static final boolean RTA_NOT_STACKABLE = false;

    /**
     * List of read switches for a later help screen.
     */
    private final Vector<String> switchList;

    /**
     * List of read parameters for a later help screen.
     */
    private final Vector<String> parameterList;

    /**
     * List of read free parameters for a later help screen.
     */
    private final Vector<String> freeParameterList;

    /**
     * List of additional descriptions for a later help screen.
     */
    private final Vector<String> descriptionList;

    /**
     * Application's name for later use in a help screen.
     */
    private final String appName;

    /**
     * Application binary's name for later use in a help screen.
     */
    private final String appBinary;

    /**
     * Application's short description for later use in a help screen.
     */
    private final String appDescription;

    /**
     * contains the List of given Parameters
     */
    private final String argv[];

    /**
     * Constructor for RTAParser.
     * 
     * @param args
     *            is a pointer to an array of strings which include all
     *            parameter strings given.
     */

    public RTAParser(String args[], String name, Class<?> clazz,
	    String description) {
	argv = args.clone(); // cloning, because Java handles references!
	switchList = new Vector<String>();
	parameterList = new Vector<String>();
	freeParameterList = new Vector<String>();
	descriptionList = new Vector<String>();
	appName = name;
	appBinary = clazz.getSimpleName();
	appDescription = description;
    }

    /**
     * getParameterString returns the string given for a special parameter. This
     * method also can only be used once per parameter. See isSwitchSet for
     * details.
     * 
     * @see RTAParser#isSwitchSet(String, String, boolean, String)
     * @param shortForm
     *            is a single character which is the short represenation of the
     *            parameter.
     * @param longForm
     *            is a string which represents the long form of the parameter.
     * @param description
     *            is a string describing the parameter. This is used for
     *            showUsage().
     * @return The parameter string is returned. If the returned length is zero,
     *         the parameter was not found!
     */
    public String getParameterString(String shortForm, String longForm,
	    String description) {
	parameterList.add(createHelpLine(shortForm, longForm, description));
	if (argv.length <= 0) {
	    return "";
	}
	String resultStr = "";
	int i = 0;
	while (i < argv.length) {
	    if (isSwitchIncluded(i, shortForm, longForm, false) != 0) {

		i++;
		if (i < argv.length) {
		    resultStr = argv[i];
		    argv[i] = "";
		}
	    }
	    i++;
	}
	return resultStr;
    }

    /**
     * This method checks whether all parameters were used or not.
     * 
     * @return The return value is true if all parameters were checked out of
     *         the parameter array. Otherwise false is returned.
     */
    public boolean isEmpty() {
	if (argv.length <= 0) {
	    return true;
	}
	for (int i = 0; i < argv.length; i++) {
	    if (argv[i].isEmpty()) {
		continue;
	    }
	    if (argv[i].charAt(0) != '-') {
		return false; // a free parameter was left
	    }
	    if (argv[i].charAt(1) == '-') {
		return false; // a long version switch was left
	    }
	    for (int j = 1; j < argv[i].length(); j++) {
		if (argv[i].charAt(j) != '.') {
		    return false; // a short switch was left
		}
	    }
	}
	return true;
    }

    /**
     * Create a formated help screen line for a parameter.
     * 
     * @param shortForm
     *            is the single character of the parameter's short form.
     * @param longForm
     *            is the name of the parameter's long form.
     * @param description
     *            is a description for the parameter and its function.
     * @return A formated line is returned as String suitable for the help
     *         screen.
     */
    private String createHelpLine(String shortForm, String longForm,
	    String description) {
	StringBuffer str = new StringBuffer();
	if ((shortForm.isEmpty()) && (longForm.isEmpty())) {
	    str.append("  <nameless parameter>");
	} else {
	    if (!shortForm.isEmpty()) {
		str.append("  -").append(shortForm);
	    } else {
		str.append("    ");
	    }
	    if (!longForm.isEmpty()) {
		str.append(" --").append(longForm);
	    }
	}
	while (str.length() < 24) {
	    str.append(" ");
	}
	str.append(description);
	return str.toString();
    }

    /**
     * This method returns the number of appearances of the parameter. This is
     * an internal method for private use and a more detail documentation is
     * therefore not needed.
     * 
     * @param index
     *            is the parameter to be searched.
     * @param shortForm
     *            is a single character which is the short represenation of the
     *            switch.
     * @param longForm
     *            is a string which represents the long form of the switch.
     * @param stackable
     *            is a boolean which sets the possibility to use more the one
     *            short form behind a single '-'.
     * @return The number of appearances of the switch are returned. There is no
     *         difference between short and long form. Each occurence is
     *         counted.
     */
    private int isSwitchIncluded(int index, String shortForm, String longForm,
	    boolean stackable) {

	int result = 0;
	if (argv.length <= 0) {
	    return result;
	}
	String s = "-" + shortForm;
	String l = "--" + longForm;
	if ((argv[index].equals(s)) || (argv[index].equals(l))) {
	    result = 1;
	    argv[index] = "";
	} else if ((argv[index].startsWith("-")) && (argv[index].length() > 1)
		&& (stackable)) {
	    if ((!(argv[index].startsWith("--"))) && (shortForm.length() > 0)) {
		for (int i = 1; i < argv[index].length(); i++) {
		    if (shortForm.charAt(0) == argv[index].charAt(i)) {
			result++;

			argv[index] = argv[index].replaceFirst(shortForm, ".");

		    }
		}
	    }
	}

	return result;
    }

    /**
     * This method searches a given parameter for forms of a parameter.
     * Attention(!): Parsing is not reproducible! Searching for a parameter can
     * only be done once. If another scan for this parameter is needed, an
     * initializeParser() is to be executed again, because the Parameter which
     * were read are overwritten in the object, so it is possible to check, that
     * all parameters were read and all parameters are used. Check out the
     * method printErrorMessage(). This is the method, which checks for all
     * parameter read and prints an error message, if there are parameters left
     * in the buffer.
     * 
     * @see RTAParser#getParameterString(String, String, String)
     * @param shortForm
     *            is a string which represents the short form of the parameter.
     * @param longForm
     *            is a string which represents the long form of the parameter.
     * @param stackable
     *            is a boolean which sets the possibility to use more the one
     *            short form behind a single '-'.
     * @param description
     *            is a string describing the parameter. This is used for
     *            showUsage().
     * @return A boolean is returned as result. TRUE is returned in the case the
     *         parameter fits and FALSE if not.
     */
    public int isSwitchSet(String shortForm, String longForm,
	    boolean stackable, String description) {

	switchList.add(createHelpLine(shortForm, longForm, description));
	int result = 0;

	if (argv.length <= 0) {
	    return result;
	}
	int i = 0;
	while (i < argv.length) {
	    result += isSwitchIncluded(i, shortForm, longForm, stackable);
	    i++;
	}
	return result;
    }

    /**
     * This method prints out an error message in the case the not all
     * parameters were checked out. The error message contains all not used
     * parameters.
     */

    public boolean printErrorMessage() {
	if (isEmpty()) {
	    return false;
	}
	System.out.println("Error! Unknown command line parameter!");
	for (int i = 0; i < argv.length; i++) {
	    if ((argv[i].length()) <= 0) {
		continue;
	    }
	    if ((argv[i].charAt(0) == '-') && (argv[i].charAt(1) != '-')) {
		for (int j = 1; j < (argv[i].length()); j++) {
		    if (argv[i].charAt(j) == '.') {
			continue;
		    }
		    System.out.println("Unknown parameter '"
			    + argv[i].charAt(j) + "' (no " + (i + 1) + ", pos "
			    + (j + 1) + ")");
		}
	    } else {
		System.out.println("Unknown parameter no.: " + (i + 1) + " "
			+ argv[i]);
	    }
	}
	return true;
    }

    /**
     * This method just reads a Vector<String> and prints the strings to
     * System.out.
     * 
     * @param list
     *            is a Vector<String> to be printed out to stdout.
     */
    private void printArgumentList(Vector<String> list) {
	if (list == null) {
	    return;
	}
	for (int i = 0; i < list.size(); i++) {
	    System.out.println(list.elementAt(i));
	}
    }

    /**
     * Prints out the help screen.
     * 
     * @param eMail
     *            is the email address for bugreports.
     */
    public void showUsage(String eMail) {
	System.out.print("\n");
	System.out.print(appBinary + "(" + appName + ")'" + appDescription
		+ "'");
	System.out.print("\n");
	System.out.print("usage: " + appBinary
		+ " [switches] [parameter] <nameless parameter>\n");
	System.out.print("\n");
	if (!switchList.isEmpty()) {
	    System.out.print("---------\n");
	    System.out.print("Switches:\n");
	    System.out.print("---------\n");
	    printArgumentList(switchList);
	    System.out.print("\n");
	}
	if (!parameterList.isEmpty()) {
	    System.out.print("----------\n");
	    System.out.print("Parameter:\n");
	    System.out.print("----------\n");
	    printArgumentList(parameterList);
	    System.out.print("\n");
	}
	if (!freeParameterList.isEmpty()) {
	    System.out.print("-------------------\n");
	    System.out.print("Nameless Parameter:\n");
	    System.out.print("-------------------\n");
	    printArgumentList(freeParameterList);
	    System.out.print("\n");
	}
	if (!descriptionList.isEmpty()) {
	    printArgumentList(descriptionList);
	    System.out.print("\n");
	}
	if (!eMail.isEmpty()) {
	    System.out.print("\n");
	    System.out.print("Report bugs to " + eMail + "\n");
	}
    }

    /**
     * Print out the version screen.
     * 
     * @param version
     *            is the version number of the tool.
     * @param year
     *            is the year range of development for the tool.
     * @param owner
     *            is the owner of the tool's source.
     * @param author
     *            is the author of the tool.
     */
    public void showVersion(String version, String year, String owner,
	    String author) {
	System.out.print("\n");
	System.out.print(appName + " " + version + "\n");
	System.out.println("Copyright (C) " + year + " " + owner);
	if (author.isEmpty()) {
	    System.out.println("Written by " + owner);
	} else {
	    System.out.println("Written by " + author);
	}
    }

    /**
     * getFreeParameterString returns the first string which does not start with
     * '-'. Attention(!): This parameters have to have a fixed position within
     * the argument list, because they are not marked with special characters!
     * 
     * @param description
     *            is a string describing the parameter. This is used for
     *            showUsage().
     * @return The parameter string is returned. If the returnlength is zero,
     *         the parameter was not found!
     */
    public String getFreeParameterString(String description) {
	freeParameterList.add(createHelpLine("", "", description));
	if (argv.length <= 0) {
	    return "";
	}

	String resultStr = "";
	int i = 0;
	while ((i < argv.length) && (resultStr.isEmpty())) {
	    if ((!argv[i].trim().isEmpty())
		    && (!argv[i].trim().startsWith("-"))) {
		resultStr = argv[i];
		// System.out.print("resultStr fParam " + resultStr);
		argv[i] = "";

	    }
	    i++;
	}
	return resultStr;
    }

    /**
     * This method adds a description line to the later help screen.
     * 
     * @param line
     *            is a String to be added to the descriptions section of the
     *            help screen.
     */
    public void addDescription(String line) {
	descriptionList.add(line);
    }

    /**
     * Standard switches are help (-h, --help), version (--version) and
     * localization (--locale). The first two are set directly in APIConfig. The
     * last is set with Translator.
     */
    public void readStandardSwitches() {
	APIInformation.setHelpRequest(isSwitchSet("h", "help",
		RTAParser.RTA_STACKABLE, "show this help page"));
	APIInformation.setVersionRequest(isSwitchSet("", "version",
		RTAParser.RTA_NOT_STACKABLE, "show current version of tool"));
    }
}
