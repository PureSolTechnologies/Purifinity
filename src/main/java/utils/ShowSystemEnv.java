package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This tool just prints all environment variables to the command line for
 * reference purpose.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ShowSystemEnv {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> keys = new ArrayList<String>();
		for (Object key : System.getenv().keySet()) {
			keys.add(key.toString());
		}
		Collections.sort(keys);
		for (String key : keys) {
			String value = System.getenv(key);
			System.out.println(key + " = " + value);
		}
	}
}
