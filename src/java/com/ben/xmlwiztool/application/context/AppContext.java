package com.ben.xmlwiztool.application.context;

import java.io.IOException;
import java.util.ResourceBundle;

import com.ben.xmlwiztool.application.context.properties.PropertiesContext;
import com.ben.xmlwiztool.application.tagname.aliaser.TagNameAliasManager;

public class AppContext {

	private static AppContext instance;
	private static PropertiesContext properties;
	private static TagNameAliasManager tagNameAliasManager;
	private static ResourceBundle bundle;

	public AppContext() {

	}

	public static void init() throws IOException {
		instance = new AppContext();
		properties = new PropertiesContext();
		tagNameAliasManager = new TagNameAliasManager();
		bundle = ResourceBundle.getBundle("i18n/trad");
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

	public TagNameAliasManager getTagNameAliasManager() {
		return tagNameAliasManager;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

}
