package com.ben.xmlwiztool.application.actions.impl;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.gui.controls.viewer.treeviewer.tab.TreeViewTab;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

public class RefreshTabsAction implements IAction {

	@Override
	public void execute() {

		GuiFacade.getInstance().getTabPane().getTabs().forEach(tab -> {

			TreeViewTab treetab = (TreeViewTab) tab;
			treetab.populate();

		});

	}

}
