package com.puresoltechnologies.purifinity.server.common.utils;

import java.util.Map;
import java.util.Properties;

/**
 * This class provides some simple utilities for console handling and output.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ConsoleUtils {

    /**
     * This method creates small status bars in ASCII art for printing in the
     * console or in log files. The status bar looks like "[********.........]"
     * without shown percentage or "[*******........] ( 45%)" with percentage
     * shown.
     * 
     * @param length
     *            is the total length of the status bar. In this length the
     *            brackets are included. The percentage number is extra length.
     * @param d
     *            is the percentage to show.
     * @param showPercentage
     *            defines whether the percentage value is to be shown.
     * @return A String is returned containing the ASCII art representation of
     *         the string
     */
    public static String createPercentageBar(int length, double d, boolean showPercentage) {
	StringBuffer buffer = new StringBuffer("[");
	int starNum = (int) Math.round(d * (length - 2));
	for (int dummy = 1; dummy <= length - 2; dummy++) {
	    if (dummy <= starNum) {
		buffer.append('*');
	    } else {
		buffer.append('.');
	    }
	}
	buffer.append(']');
	if (showPercentage) {
	    buffer.append(" (");
	    StringBuffer num = new StringBuffer(String.valueOf((int) Math.round(d * 100.0)));
	    while (num.length() < 3) {
		num.insert(0, ' ');
	    }
	    buffer.append(num);
	    buffer.append("%)");
	}
	return buffer.toString();
    }

    public static void printSystemProperties() {
	Properties properties = System.getProperties();
	for (Object key : properties.keySet()) {
	    System.out.println(key.toString() + ":\n\t'" + properties.get(key) + "'");
	}
    }

    public static void printEnvironment() {
	Map<String, String> env = System.getenv();
	for (String key : env.keySet()) {
	    System.out.println(key.toString() + ":\n\t'" + env.get(key) + "'");
	}
    }
}
