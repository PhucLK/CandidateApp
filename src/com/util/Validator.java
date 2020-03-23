/**
 * 
 *
 */
package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.exceptions.BirthdayException;
import com.exceptions.MailException;

/**
 * @author PhucTV7
 *
 */
public class Validator {

	private static Matcher matcher;
	private static Pattern pattern;

	/**
	 * @param phone
	 * @return true if phone number is valid
	 */
	public static boolean isPhoneNumber(String phone) {
		pattern = Pattern.compile(Constant.INCORECT_PHONE_NUMBER);
		matcher = pattern.matcher(phone);
		return matcher.matches();
	}

	/**
	 * @param email
	 * @return true if email is valid
	 * @throws MailException if email not valid
	 */
	public static boolean isEmail(String email) throws MailException {
		pattern = Pattern.compile(Constant.INCORECT_EMAIL);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * @param birthday
	 * @return true if birthday is valid
	 * @throws BirthdayException if birthday < 1990 && birthday > now date
	 */
	public static boolean isBirthDay(String birthday) throws BirthdayException {

		@SuppressWarnings("deprecation")
		Date d = new Date(1990, 01, 01);
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
			if (date.after(d) && date.before(new Date())) {
				return true;
			} else {
				throw new BirthdayException();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

}
