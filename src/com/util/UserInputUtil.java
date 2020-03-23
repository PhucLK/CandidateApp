/**
 * 
 *
 */
package com.util;

import java.text.ParseException;
import java.util.Scanner;

import com.exceptions.BirthdayException;
import com.exceptions.MailException;

/**
 * @author PhucTV7
 *
 */
public class UserInputUtil {

	/**
	 * @param value
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
				System.out.println("Please input int value!");
				continue;
			}
			break;
		}
		return a;
	}

	/**
	 * @param email
	 * @return String is a valid email
	 */
	public static String inputEmail(Scanner sc) {
		String email = "";
		System.out.println("Enter an email");
		try {
			email = sc.nextLine();
			while (!Validator.isEmail(email)) {
				System.out.println("Sorry! Email not valid, Try again :");
			}
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return email;
	}

	/**
	 * @param sc
	 * @return String is a valid phone number
	 */
	public static String inputPhone(Scanner sc) {
		System.out.println("Enter an phone number");
		String phone = sc.nextLine();

		while (!Validator.isPhoneNumber(phone)) {
			System.out.println("Sorry! Phone number not valid, Try again :");
		}

		return phone;
	}

	/**
	 * @param sc
	 * @return String is a valid birthday
	 * @throws ParseException
	 */
	public static String inputBirthDay(Scanner sc) {
		System.out.println("Enter an birthday(yyyy/MM/dd) :");
		String birthday = sc.nextLine();

		do {
			try {
				while (!Validator.isBirthDay(birthday)) {
					System.out.println("Sorry! Birthday not valid, Try again :");
				}
			} catch (BirthdayException e) {
				// TODO: handle exception
				System.out.println("Sorry! Birthday not valid, Try again :");
			}
			break;
		} while (true);

		return birthday;
	}

}
