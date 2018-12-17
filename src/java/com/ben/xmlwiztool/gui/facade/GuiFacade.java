package com.ben.xmlwiztool.gui.facade;

import java.util.List;
import java.util.stream.Collectors;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TabPane;

public class GuiFacade {

	private static GuiFacade instance;

	private SimpleDoubleProperty tabSize;

	private String separator = ".";

	private TabPane tabPane;

	private GuiFacade() {

		tabSize = new SimpleDoubleProperty();

		Double value = Double.valueOf(AppContext.getInstance().getProperties().get("tabSize"));
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

	public void toggleSeparator() {

		separator = separator == "." ? "\\" : ".";

	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public List<ElementWrapper> getOpenElements() {

		return tabPane.getTabs().stream().map(tab -> (ElementWrapper) tab.getUserData()).collect(Collectors.toList());

	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

}
