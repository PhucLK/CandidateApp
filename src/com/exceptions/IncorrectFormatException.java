/**
 * 
 *
 */
package com.exceptions;

/**
 * @author PhucTV7
 *
 */
public class IncorrectFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public IncorrectFormatException() {
	}

	/**
	 * @param message
	 */
	public IncorrectFormatException(String message) {
		super(message);
	}

}
