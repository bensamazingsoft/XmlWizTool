package com.ben.xmlwiztool.gui.facade;

import com.ben.xmlwiztool.application.context.AppContext;

import javafx.beans.property.SimpleDoubleProperty;

public class GuiFacade {

	private static GuiFacade instance;

	SimpleDoubleProperty tabSize;

	private GuiFacade() {

		tabSize = new SimpleDoubleProperty();

		Double value = Double.valueOf(AppContext.getProperties().get("tabSize"));
		tabSize.setValue(value);

	}

	public static GuiFacade getInstance() {

		if (instance == null) {
			instance = new GuiFacade();
		}

		return instance;

	}

	public SimpleDoubleProperty getTabSize() {
		return tabSize;
	}

	public void setTabSize(SimpleDoubleProperty tabSize) {
		this.tabSize = tabSize;
	}

}
