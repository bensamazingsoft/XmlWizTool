package com.ben.xmlwiztool.application.document.exception;

import java.util.ResourceBundle;

public class DocumentParsingException extends Exception {
	String message;

	public DocumentParsingException() {
		super();
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		message = bundle.getString("DocumentParsingException");

	}

	@Override
	public String getMessage() {

		return message;
	}

}
