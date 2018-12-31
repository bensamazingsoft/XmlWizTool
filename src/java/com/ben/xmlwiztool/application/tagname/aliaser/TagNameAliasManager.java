package com.ben.xmlwiztool.application.tagname.aliaser;

import java.util.HashMap;
import java.util.Map;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.context.properties.PropertiesContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

import javafx.beans.property.SimpleStringProperty;

public class TagNameAliasManager {

	private final Map<ElementWrapper, Map<ElementWrapper, SimpleStringProperty>> nameMap = new HashMap<>();
	// move regex to properties and inject while init Appcontext
	public final static String REGEX = "^((?:(?:\\w+\\.)+)[^\\.]+$)";

	public static PropertiesContext prop = AppContext.getInstance().getProperties();

	public TagNameAliasManager() {

	}

	public Map<ElementWrapper, SimpleStringProperty> get(ElementWrapper wrapper) {

		return nameMap.get(wrapper);
	}

	public SimpleStringProperty getAlias(ElementWrapper rootWrapper, ElementWrapper wrapper) {

		SimpleStringProperty alias = new SimpleStringProperty();

		if (nameMap.keySet().contains(rootWrapper)) {

			Map<ElementWrapper, SimpleStringProperty> tags = nameMap.get(rootWrapper);

			if (tags.keySet().contains(wrapper)) {

				return tags.get(wrapper);

			}

		}

		return alias;
	}

	public static String getDefaultTagNameAlias(ElementWrapper wrapper) {

		// if new source to get aliases, it goes here
		if (aliasable(wrapper)) {

			if (prop.get(wrapper.getElement().getTagName()) != null) {

				return wrapper.getElement().getTagName();

			}

			String[] tags = wrapper.getElement().getTagName().split("\\.");

			return tags[tags.length - 1];

		}
		return wrapper.getElement().getTagName();
	}

	public static boolean aliasable(ElementWrapper wrapper) {

		boolean result = wrapper.getElement().getTagName().matches(REGEX);

		return result;
	}

	public boolean aliased(ElementWrapper wrapper) {

		for (Map<ElementWrapper, SimpleStringProperty> map : nameMap.values()) {
			if (map.keySet().contains(wrapper)) {
				return true;
			}
		}
		return false;

	}

	public Map<ElementWrapper, Map<ElementWrapper, SimpleStringProperty>> getNameMap() {
		return nameMap;
	}

}
