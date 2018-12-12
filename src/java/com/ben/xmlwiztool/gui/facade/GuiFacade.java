package com.ben.xmlwiztool.gui.facade;

import javafx.beans.property.SimpleDoubleProperty;

public class GuiFacade {

	private static GuiFacade instance;

	SimpleDoubleProperty tabSize;

	private GuiFacade() {

	}

	public static GuiFacade getInstance() {

		if (instance == null) {
			instance = new GuiFacade();
		}

		return instance;

	}

}
