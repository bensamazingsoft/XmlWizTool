package com.ben.xmlwiztool.application.actions.exception;

import java.util.ResourceBundle;

public class NullFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;

	public NullFileException() {
		super();
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		message = bundle.getString("NullFileException");

	}

	@Override
	public String getMessage() {

		return message;
	}

}
