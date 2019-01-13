
package com.ben.xmlwiztool.gui.controls.menu.context.treeview;

import com.ben.xmlwiztool.application.actions.impl.OpenLeafViewAction;
import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class TreeViewContextMenu extends ContextMenu
{

      MenuItem detailMenuItem;


      public TreeViewContextMenu(ElementWrapper wrapper)
      {

	    detailMenuItem = new MenuItem(AppContext.getInstance().getBundle().getString("treeviewcontextmenuitem1"));
	    detailMenuItem.setOnAction((event) -> {
		  new OpenLeafViewAction(wrapper).execute();

	    });

	    getItems().add(detailMenuItem);

      }

}
