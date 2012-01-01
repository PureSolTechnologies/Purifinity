package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This tool just prints all standard system properties to the command line for
 * reference purpose.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ShowSystemProperties {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> keys = new ArrayList<String>();
		for (Object key : System.getProperties().keySet()) {
			keys.add(key.toString());
		}
		Collections.sort(keys);
		for (String key : keys) {
			String value = System.getProperty(key);
			System.out.println(key + " = " + value);
		}
	}
}
