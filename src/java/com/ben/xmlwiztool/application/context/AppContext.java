package com.ben.xmlwiztool.application.context;

import java.io.IOException;

import com.ben.xmlwiztool.application.context.properties.PropertiesContext;

public class AppContext {

	private static AppContext instance;
	private static PropertiesContext properties;

	public AppContext() {

	}

	public static void init() throws IOException {
		instance = new AppContext();
		properties = new PropertiesContext();
	}

	public static AppContext getInstance() {

		if (instance == null) {
			throw new RuntimeException("AppContext instance null");
		}

		return instance;
	}

	public PropertiesContext getProperties() {
		return properties;
	}

	public void setProperties(PropertiesContext properties) {
		AppContext.properties = properties;
	}

}
