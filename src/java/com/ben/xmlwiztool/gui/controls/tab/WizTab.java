package com.ben.xmlwiztool.gui.controls.tab;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.gui.viewer.ElementViewer;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;

public class WizTab extends Tab {

	public WizTab(ElementWrapper wrapper) {
		super();
		this.setUserData(wrapper);
		this.setText(wrapper.getElement().getTagName());

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(new ElementViewer(wrapper));

		this.setContent(scrollPane);
	}

	@Override
	public ElementWrapper getUserData() {

		return (ElementWrapper) super.getUserData();
	}

	public String getName() {
		return getUserData().getElement().getTagName();
	}

}
