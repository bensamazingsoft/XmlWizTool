package com.ben.xmlwiztool.gui.controls.settings.factory;

import java.util.HashMap;
import java.util.Map;

import com.ben.xmlwiztool.application.restorable.Restorable;

import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.control.Control;

public class SettingcontrolFactory {

	private static Map<Class, Provider> dispatch = new HashMap<>();

	private interface Provider {
		public Control provide(Property prop);
	}

	@SuppressWarnings("rawtypes")
	public static Node getcontrol(Restorable restorable) {

		return null;
	}

}
