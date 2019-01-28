package com.ben.gui.fx.property.wrapper.impl;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.gui.controls.viewer.tabfactory.TabFactory;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.scene.control.TabPane;

public class OpenSoloViewAction implements IAction {

	private final ElementWrapper wrapper;

	public OpenSoloViewAction(ElementWrapper wrapper) {

		this.wrapper = wrapper;
	}

	@Override
	public void execute() {

		TabPane tabPane = GuiFacade.getInstance().getTabPane();
		tabPane.getTabs().add(TabFactory.getTab(wrapper, TabFactory.Type.TREE));

	}
}
