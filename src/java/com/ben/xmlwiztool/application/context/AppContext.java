package com.ben.xmlwiztool.application.context;

import java.io.IOException;

import com.ben.xmlwiztool.application.context.properties.PropertiesContext;

public class AppContext {

	private static AppContext instance;
	private static PropertiesContext properties;

	public AppContext() {

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

}
