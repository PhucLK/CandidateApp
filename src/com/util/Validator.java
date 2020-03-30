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
		pattern = Pattern.compile(Constant.PHONE_NUMBER);
		matcher = pattern.matcher(phone);
		return matcher.matches();
	}

	/**
	 * @param email
	 * @return true if email is valid
	 * @throws MailException if email not valid
	 */
	public static boolean isEmail(String email) throws MailException {
		pattern = Pattern.compile(Constant.EMAIL);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * @param birthday
	 * @return true if birthday is valid
	 * @throws BirthdayException if birthday < 1990 or birthday > current date
	 * @throws ParseException
	 */
	public static boolean isBirthDay(String birthday) throws BirthdayException, ParseException {

		String d1990 = "1990/01/01";
		Date day1990;
		Date b;
		b = new SimpleDateFormat("yyyy/MM/dd").parse(birthday);
		day1990 = new SimpleDateFormat("yyyy/MM/dd").parse(d1990);
		if (b.after(day1990) && b.before(new Date())) {
			return true;
		} else {
			throw new BirthdayException();
		}

	}

}
