package com.ben.xmlwiztool.application.context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ben.xmlwiztool.application.context.properties.PropertiesContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

public class AppContext {

	private static AppContext instance;
	private static PropertiesContext properties;
	private static List<ElementWrapper> openDocuments;

	public AppContext() {

		openDocuments = new ArrayList<>();

	}

	public static void init() throws IOException {
		properties = new PropertiesContext();
	}

	public static AppContext getInstance() {
		return instance;
	}

	public static PropertiesContext getProperties() {
		return properties;
	}

	public void setProperties(PropertiesContext properties) {
		AppContext.properties = properties;
	}

	public static List<ElementWrapper> openDocuments() {

		return openDocuments;
	}

}
