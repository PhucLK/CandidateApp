package com.exceptions;

/**
 * @author PhucTV7
 *
 */
public class MailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MailException() {
	}

	/**
	 * @param email
	 * @return true if email is correct otherwise return false
	 */
	public MailException(String message) {
		super(message);
	}

}
