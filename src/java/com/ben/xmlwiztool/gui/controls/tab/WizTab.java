
package com.ben.xmlwiztool.gui.controls.tab;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.gui.facade.GuiFacade;
import com.ben.xmlwiztool.gui.viewer.ElementViewer;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;

public class WizTab extends Tab {

	public WizTab(ElementWrapper wrapper) {

		super();
		this.setUserData(wrapper);
		this.setText(wrapper.getElement().getTagName());
		this.setOnClosed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

			}
		});

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(new ElementViewer(wrapper));

		this.setContent(scrollPane);

		GuiFacade.getInstance().getTabPane().getSelectionModel().select(this);

	}

	@Override
	public ElementWrapper getUserData() {

		return (ElementWrapper) super.getUserData();
	}

	public String getName() {

		return getUserData().getElement().getTagName();
	}

}
