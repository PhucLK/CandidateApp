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
	 * @param message
	 */
	public MailException(String message) {
		super(message);
	}

}
