
package com.ben.xmlwiztool.application.actions.impl;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.gui.controls.viewer.treeviewer.tab.LeafViewTab;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.scene.control.TabPane;

public class OpenLeafViewAction implements IAction
{

      private final ElementWrapper wrapper;


      public OpenLeafViewAction(ElementWrapper wrapper)
      {

	    this.wrapper = wrapper;
      }


      @Override
      public void execute()
      {

	    TabPane tabPane = GuiFacade.getInstance().getTabPane();
	    tabPane.getTabs().add(new LeafViewTab(wrapper));

      }

}
