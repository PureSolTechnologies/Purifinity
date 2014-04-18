/* 
 * Name: 
 * 		PasswordCheck.java
 * Author: 
 * 		Jim Sloey - jsloey@justwild.us
 * Requirements:
 * 		Java 1.4 or greater
 * Usage:
 *		Bundled usage: java -jar PasswordCheck.jar <password>
 *		Unbundled usage: java PasswordCheck <password>
 * History:
 * 		Created May 19, 2006 by Jim Sloey
 * Derived from: 
 * 		Steve Moitozo's passwdmeter
 * 		See http://www.geekwisdom.com/dyn/passwdmeter
 * License:
 * 		Open Software License 2.1 or Academic Free License 2.1 
 * 		See http://www.opensource.org
 * Description:
 * 		Need a simple way to check the strength of a password?
 * 		To check in the HTML on the front end try Steve Moitozo's 
 * 		Javascript example at http://www.geekwisdom.com/dyn/passwdmeter
 * Source URL:
 * 		http://justwild.us/examples/password/
 */

package com.puresoltechnologies.purifinity.server.passwordstore.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class was inspired by Steve Moitozo's passwdmeter
 * (http://www.geekwisdom.com/dyn/passwdmeter).
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PasswordStrengthCalculator {

	private final static String PASSWORD_CHECK_PROPERTY_FILE = "/META-INF/PasswordCheck.properties";

	// Rules variables
	private static int PASSWORD_MIXED_CASE;
	private static int PASSWORD_MIN_LENGTH;
	private static int PASSWORD_MAX_LENGTH;
	private static int PASSWORD_NUMERIC;
	private static int PASSWORD_SPECIAL;
	private static int PASSWORD_STRENGTH;

	static {
		Properties properties = new Properties();
		try {
			InputStream inputStream = PasswordStrengthCalculator.class
					.getResourceAsStream(PASSWORD_CHECK_PROPERTY_FILE);
			if (inputStream == null) {
				throw new RuntimeException("Could not find '"
						+ PASSWORD_CHECK_PROPERTY_FILE
						+ "' for password strength settings");
			}
			try {
				properties.load(inputStream);
				PASSWORD_MIXED_CASE = Integer.valueOf(properties
						.getProperty("password.mixed.case"));
				PASSWORD_MAX_LENGTH = Integer.valueOf(properties
						.getProperty("password.max.length"));
				PASSWORD_MIN_LENGTH = Integer.valueOf(properties
						.getProperty("password.min.length"));
				PASSWORD_NUMERIC = Integer.valueOf(properties
						.getProperty("password.numeric"));
				PASSWORD_SPECIAL = Integer.valueOf(properties
						.getProperty("password.special"));
				PASSWORD_STRENGTH = Integer.valueOf(properties
						.getProperty("password.strength"));
			} finally {
				inputStream.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(
					"Could not read properties for PasswordCheck! (PasswordCheck.properties)",
					e);
		}
	}

	/**
	 * This is an easy, static method to check a password's strength.
	 * 
	 * @param password
	 *            is the password string to check.
	 * @return True is returned if the password is strong enough. False is
	 *         returned otherwise.
	 */
	public static boolean validate(String password) {
		return new PasswordStrengthCalculator(password).isValid;
	}

	private int upper = 0;
	private int lower = 0;
	private int numbers = 0;
	private int special = 0;
	private int length = 0;
	private int intScore = 0;
	private boolean isValid = false;
	private PasswordStrength strength = PasswordStrength.VERY_WEAK;
	private String strLog = "";

	public PasswordStrengthCalculator(String password) {
		checkPasswordStrength(password);
	}

	private void checkPasswordStrength(String passwd) {
		if (passwd == null)
			return;
		// PASSWORD LENGTH
		length = passwd.length();
		if (length < 5) // length 4 or less
		{
			intScore += 3;
			strLog = strLog + "3 points for length (" + length + ")\n";
		} else if (length > 4 && passwd.length() < 8) // length between 5 and 7
		{
			intScore += 6;
			strLog = strLog + "6 points for length (" + length + ")\n";
		} else if (length > 7 && passwd.length() < 16) // length between 8 and
														// 15
		{
			intScore += 12;
			strLog = strLog + "12 points for length (" + length + ")\n";
		} else if (length > 15) // length 16 or more
		{
			intScore += 18;
			strLog = strLog + "18 point for length (" + length + ")\n";
		}
		// LETTERS
		Pattern p = Pattern.compile(".??[a-z]");
		Matcher m = p.matcher(passwd);
		while (m.find()) // [verified] at least one lower case letter
		{
			lower += 1;
		}
		if (lower > 0) {
			intScore += 1;
			strLog = strLog + "1 point for a lower case character\n";
		}
		p = Pattern.compile(".??[A-Z]");
		m = p.matcher(passwd);
		while (m.find()) // [verified] at least one upper case letter
		{
			upper += 1;
		}
		if (upper > 0) {
			intScore += 5;
			strLog = strLog + "5 point for an upper case character\n";
		}
		// NUMBERS
		p = Pattern.compile(".??[0-9]");
		m = p.matcher(passwd);
		while (m.find()) // [verified] at least one number
		{
			numbers += 1;
		}
		if (numbers > 0) {
			intScore += 5;
			strLog = strLog + "5 points for a number\n";
			if (numbers > 1) {
				intScore += 2;
				strLog = strLog + "2 points for at least two numbers\n";
				if (numbers > 2) {
					intScore += 3;
					strLog = strLog + "3 points for at least three numbers\n";
				}
			}
		}
		// SPECIAL CHAR
		p = Pattern.compile(".??[:,!,@,#,$,%,^,&,*,?,_,~]");
		m = p.matcher(passwd);
		while (m.find()) // [verified] at least one special character
		{
			special += 1;
		}
		if (special > 0) {
			intScore += 5;
			strLog = strLog + "5 points for a special character\n";
			if (special > 1) {
				intScore += 5;
				strLog = strLog
						+ "5 points for at least two special characters\n";
			}
		}
		// COMBOS
		if (upper > 0 && lower > 0) // [verified] both upper and lower case
		{
			intScore += 2;
			strLog = strLog + "2 combo points for upper and lower letters\n";
		}
		if ((upper > 0 || lower > 0) && numbers > 0) // [verified] both letters
														// and numbers
		{
			intScore += 2;
			strLog = strLog + "2 combo points for letters and numbers\n";
		}
		if ((upper > 0 || lower > 0) && numbers > 0 && special > 0) // [verified]
																	// letters,
																	// numbers,
																	// and
																	// special
																	// characters
		{
			intScore += 2;
			strLog = strLog
					+ "2 combo points for letters, numbers and special chars\n";
		}
		if (upper > 0 && lower > 0 && numbers > 0 && special > 0) // [verified]
																	// upper,
																	// lower,
																	// numbers,
																	// and
																	// special
																	// characters
		{
			intScore += 2;
			strLog = strLog
					+ "2 combo points for upper and lower case letters, numbers and special chars\n";
		}
		if (intScore < 16) {
			strength = PasswordStrength.VERY_WEAK;
		} else if (intScore > 15 && intScore < 25) {
			strength = PasswordStrength.WEAK;
		} else if (intScore > 24 && intScore < 35) {
			strength = PasswordStrength.MEDIOCRE;
		} else if (intScore > 34 && intScore < 45) {
			strength = PasswordStrength.STRONG;
		} else {
			strength = PasswordStrength.VERY_STRONG;
		}

		// Does it meet the password policy?
		if (length < PASSWORD_MIN_LENGTH)
			return;
		if (length > PASSWORD_MAX_LENGTH)
			return;
		if (numbers < PASSWORD_NUMERIC)
			return;
		if (upper < PASSWORD_MIXED_CASE || lower < PASSWORD_MIXED_CASE)
			return;
		if (intScore < PASSWORD_STRENGTH)
			return;
		if (special < PASSWORD_SPECIAL)
			return;
		isValid = true;
	}

	@Override
	public String toString() {
		return strength.toString() + " - " + intScore + "\n" + strLog;
	}

	public int getUpper() {
		return upper;
	}

	public int getLower() {
		return lower;
	}

	public int getNumbers() {
		return numbers;
	}

	public int getSpecial() {
		return special;
	}

	public int getLength() {
		return length;
	}

	public int getIntScore() {
		return intScore;
	}

	public boolean isValid() {
		return isValid;
	}

	public PasswordStrength getStrength() {
		return strength;
	}

	public String getLog() {
		return strLog;
	}
}
