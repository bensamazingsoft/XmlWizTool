package com.ben.xmlwiztool.application.tagname.aliaser;

import java.util.HashMap;
import java.util.Map;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

public class TagNameAliasManager {

	private final Map<ElementWrapper, Map<ElementWrapper, String>> nameMap = new HashMap<>();
	// move regex to properties and inject while init Appcontext
	public final static String REGEX = "^((?:(?:\\w+\\.)+)[^\\.]+$)";

	public TagNameAliasManager() {

	}

	public Map<ElementWrapper, String> get(ElementWrapper wrapper) {

		return nameMap.get(wrapper);
	}

	public String getAlias(ElementWrapper rootWrapper, ElementWrapper wrapper) {

		String alias = "";

		if (nameMap.keySet().contains(rootWrapper)) {

			Map<ElementWrapper, String> tags = nameMap.get(rootWrapper);

			if (tags.keySet().contains(wrapper)) {

				return tags.get(wrapper);

			}

		}

		return alias;
	}

	public static String getTagNameAlias(ElementWrapper wrapper) {

		// if new source to get aliases, it goes here
		if (aliasable(wrapper)) {

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

		for (Map<ElementWrapper, String> map : nameMap.values()) {
			if (map.keySet().contains(wrapper)) {
				return true;
			}
		}
		return false;

		// Set<ElementWrapper> set =
		// nameMap.values().stream().map(Map::keySet).flatMap(Set::stream)
		// .collect(Collectors.toSet());
		//
		// return set.contains(wrapper);

	}

	public Map<ElementWrapper, Map<ElementWrapper, String>> getNameMap() {
		return nameMap;
	}

}
