
package com.ben.xmlwiztool.gui.facade;

import java.util.List;
import java.util.stream.Collectors;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TabPane;

public class GuiFacade {

	private static GuiFacade instance;

	private SimpleDoubleProperty tabSize;
	private SimpleDoubleProperty tabLength;

	private String separator = ".";

	private TabPane tabPane;

	private SimpleBooleanProperty hideEmpty;

	private GuiFacade() {

		tabSize = new SimpleDoubleProperty();
		tabLength = new SimpleDoubleProperty();

		Double value = Double.valueOf(AppContext.getInstance().getProperties().get("tabSize"));
		Double length = Double.valueOf(AppContext.getInstance().getProperties().get("tabLength"));

		tabSize.setValue(value);
		tabLength.setValue(length);

		hideEmpty = new SimpleBooleanProperty();

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

		// TODO implement change in Stickers
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

	public final SimpleDoubleProperty tabLengthProperty() {

		return this.tabLength;
	}

	public final double getTabLength() {

		return this.tabLengthProperty().get();
	}

	public final void setTabLength(final double tabLength) {

		this.tabLengthProperty().set(tabLength);
	}

	public final SimpleBooleanProperty hideEmptyProperty() {
		return this.hideEmpty;
	}

	public final boolean isHideEmpty() {
		return this.hideEmptyProperty().get();
	}

	public final void setHideEmpty(final boolean hideEmpty) {
		this.hideEmptyProperty().set(hideEmpty);
	}

}
