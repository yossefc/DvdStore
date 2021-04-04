//fait dans cette classe on verifie les noms date code
package in.co.dvd.store.util;

import java.text.ParseException;
import java.util.Date;


public class DataValidator {
	/**
	 * verifie the name is english or hebrew
	 */
	public static boolean isName(String val) {

		String name ="^[A-Za-zà-ú ]*$";
		if (val.matches(name)) {
			return true;
		} else {
			return false;
		}
	}
	
	//verifie je ne sais pas cest quoi!!!!
	public static boolean isRollNO(String val) {
		String passregex = "^([0-9]{2}[A-Z]{2}[0-9]{1,})\\S$";

		if (val.matches(passregex)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Password
	 * At least one upper case English letter
	 * At least one lower case English letter
	 * At least one digit
	 * At least one special character
	 * Minimum eight in length 
	 * https://stackoverflow.com/questions/19605150/regex-for-password-must-contain-at-least-eight-characters-at-least-one-number-a
	 * @param val
	 * @return boolean
	 */
	public static boolean isPassword(String val) {
		String passregex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

		if (val.matches(passregex)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Phone No
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isPhoneNo(String val) {
		String regex = "^[0][5][0-8]{1}[0-9]{7}$";
		if (val.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Null
	 * fait!!
	 * @param val
	 * @return boolean
	 */
	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is NOT Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	/**
	 * Checks if value is an Integer
	 * 
	 * @param val
	 * @return
	 */

	public static boolean isInteger(String val) {

		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Long
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				long i = Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}


	/**
	 * Checks if value is valid Email ID
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isEmail(String val) {

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}




}
