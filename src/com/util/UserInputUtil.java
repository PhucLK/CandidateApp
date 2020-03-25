/**
 * 
 *
 */
package com.util;

import java.text.ParseException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.exceptions.BirthdayException;
import com.exceptions.MailException;

/**
 * @author PhucTV7
 *
 */
public class UserInputUtil {

	final static Logger logger = Logger.getLogger(UserInputUtil.class);

	/**
	 * @param sc
	 * @return a valid int number
	 */
	public static int inputTypeInt(Scanner sc) {
		int a = 0;
		while (true) {
			try {
				String value = sc.nextLine();
				a = Integer.parseInt(value);
			} catch (Exception e) {
				// TODO: handle exception
				logger.error(e.toString());
				System.out.println("Please input int value!");
				continue;
			}
			break;
		}
		return a;
	}

	/**
	 * @param sc
	 * @return String is a valid email
	 */
	public static String inputEmail(Scanner sc) {
		String email = "";
		while (true) {
			try {
				email = sc.nextLine();
				if (!Validator.isEmail(email)) {
					System.out.println("Sorry! Email not valid, Try again :");
					continue;
				} else
					return email;
			} catch (MailException e) {
				logger.error(e.toString());
				System.out.println("Sorry! Email not valid, Try again :");
				continue;
			}
		}
	}

	/**
	 * @param sc
	 * @return String is a valid phone number
	 */
	public static String inputPhone(Scanner sc) {
		String phone;
		while (true) {
			phone = sc.nextLine();
			if (!Validator.isPhoneNumber(phone)) {
				System.out.println("Sorry! Phone number not valid, Try again :");
				continue;
			} else
				return phone;
		}
	}

	/**
	 * @param sc
	 * @return String is a valid birthday
	 * 
	 */
	public static String inputBirthDay(Scanner sc) {
		String birthday = null;
		while (true) {
			try {
				birthday = sc.nextLine();
				if (!Validator.isBirthDay(birthday)) {
					System.out.println("Sorry! Birthday not valid, Try again :");
					continue;
				} else
					return birthday;
			} catch (BirthdayException | ParseException e) {
				// TODO: handle exception
				logger.error(e.toString());
				System.out.println("Sorry! Birthday not valid, Try again :");
				continue;
			}
		}
	}

}
