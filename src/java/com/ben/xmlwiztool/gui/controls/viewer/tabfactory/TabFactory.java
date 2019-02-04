package com.ben.xmlwiztool.gui.controls.viewer.tabfactory;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.gui.controls.viewer.tab.StickerTab;
import com.ben.xmlwiztool.gui.controls.viewer.treeviewer.tab.LeafViewTab;
import com.ben.xmlwiztool.gui.controls.viewer.treeviewer.tab.TreeViewTab;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.scene.control.Tab;

public class TabFactory {

	public static Tab getTab(ElementWrapper wrapper, Type type) {

		Tab tab = new Tab();

		switch (type) {
		case LEAF:
			tab = new LeafViewTab(wrapper);
			break;
		case TREE:
			boolean treeView = GuiFacade.getInstance().isTreeView();
			tab = treeView ? new TreeViewTab(wrapper) : new StickerTab(wrapper);
			break;

		default:
			break;

		}
		return tab;
	}

	public enum Type {
		TREE, LEAF;
	}

}
