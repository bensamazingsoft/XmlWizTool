
package com.ben.xmlwiztool.application.actions.impl;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.gui.controls.viewer.treeviewer.tab.TreeViewTab;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

public class ReloadActiveTabAction implements IAction
{

      @Override
      public void execute()
      {

	    TreeViewTab tab = (TreeViewTab) GuiFacade.getInstance().getTabPane().getSelectionModel().selectedItemProperty().get();

	    tab.populate();

      }

}
