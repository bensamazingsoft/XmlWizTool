package com.ben.xmlwiztool.application.document.exception;

import java.util.ResourceBundle;

public class InvalidFileFormatException extends Exception {

	String message;

	public InvalidFileFormatException() {
		super();
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		message = bundle.getString("invalidFileFormatException");

	}

	public InvalidFileFormatException(String message) {
		super(message);
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		message = bundle.getString(message);
	}

	@Override
	public String getMessage() {

		return message;
	}

}
