package com.ben.xmlwiztool.gui.controls.tab.factory;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.gui.controls.tab.StickerTab;
import com.ben.xmlwiztool.gui.controls.tab.TreeViewTab;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.scene.control.Tab;

public class TabFactory {

	public static Tab getTab(ElementWrapper wrapper) {

		boolean treeView = GuiFacade.getInstance().isTreeView();

		return treeView ? new TreeViewTab(wrapper) : new StickerTab(wrapper);

	}

}
