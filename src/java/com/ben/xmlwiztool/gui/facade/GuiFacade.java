
package com.ben.xmlwiztool.gui.facade;

import java.util.List;
import java.util.stream.Collectors;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TabPane;

public class GuiFacade {

	private static GuiFacade instance;

	private SimpleDoubleProperty tabSize;

	private SimpleStringProperty separator;
	private final String POINT = ".";
	private final String SLASH = "\\";

	private TabPane tabPane;

	private SimpleBooleanProperty hideEmpty, fastLoad, treeView;

	private GuiFacade() {

		separator = new SimpleStringProperty(POINT);

		tabSize = new SimpleDoubleProperty();

		Double value = Double.valueOf(AppContext.getInstance().getProperties().get("tabSize"));

		tabSize.setValue(value);

		hideEmpty = new SimpleBooleanProperty();

		fastLoad = new SimpleBooleanProperty();
		fastLoad.set(Boolean.valueOf(AppContext.getInstance().getProperties().get("fastLoad")));
		fastLoad.addListener((o, oldVal, newVal) -> {
			AppContext.getInstance().getProperties().set("fastLoad", newVal.toString());
		});

		treeView = new SimpleBooleanProperty();
		treeView.set(Boolean.valueOf(AppContext.getInstance().getProperties().get("treeView")));
		treeView.addListener((o, oldVal, newVal) -> {
			AppContext.getInstance().getProperties().set("treeView", newVal.toString());
		});

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

		separator.set(separator.get().equals(POINT) ? SLASH : POINT);

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

	public final SimpleBooleanProperty hideEmptyProperty() {
		return this.hideEmpty;
	}

	public final boolean isHideEmpty() {
		return this.hideEmptyProperty().get();
	}

	public final void setHideEmpty(final boolean hideEmpty) {
		this.hideEmptyProperty().set(hideEmpty);
	}

	public final SimpleStringProperty separatorProperty() {
		return this.separator;
	}

	public final String getSeparator() {
		return this.separatorProperty().get();
	}

	public final void setSeparator(final String separator) {
		this.separatorProperty().set(separator);
	}

	public final SimpleBooleanProperty fastLoadProperty() {
		return this.fastLoad;
	}

	public final boolean isFastLoad() {
		return this.fastLoadProperty().get();
	}

	public final void setFastLoad(final boolean fastLoad) {
		this.fastLoadProperty().set(fastLoad);
	}

	public final SimpleBooleanProperty treeViewProperty() {
		return this.treeView;
	}

	public final boolean isTreeView() {
		return this.treeViewProperty().get();
	}

	public final void setTreeView(final boolean treeView) {
		this.treeViewProperty().set(treeView);
	}

}
