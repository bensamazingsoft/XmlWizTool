
package com.ben.xmlwiztool.gui.controls.viewer.treeviewer.tab;

import java.util.Map;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.gui.controls.menu.context.treeview.TreeViewContextMenu;
import com.ben.xmlwiztool.gui.controls.viewer.tab.WizTab;
import com.ben.xmlwiztool.gui.controls.viewer.treeviewer.cell.WizTreeCell;
import com.ben.xmlwiztool.gui.facade.GuiFacade;
import com.ben.xmlwiztool.gui.treeviewer.item.WrapperTreeItem;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

public class TreeViewTab extends WizTab
{

      private ScrollPane scrollPane = new ScrollPane();


      public TreeViewTab(ElementWrapper wrapper)
      {

	    super();

	    this.wrapper = wrapper;

	    this.setUserData(wrapper);
	    this.setText(wrapper.getElement().getTagName());
	    this.setOnClosed((event) -> {
		  onClose(wrapper);
	    });
	    this.setContextMenu(new TreeViewContextMenu(wrapper));

	    populate();

	    GuiFacade.getInstance().getTabPane().getSelectionModel().select(this);

      }


      @Override
      public void populate()
      {

	    scrollPane = new ScrollPane();

	    TreeView<ElementWrapper> treeView = new TreeView<ElementWrapper>(new WrapperTreeItem(wrapper));
	    treeView.setCellFactory(new Callback<TreeView<ElementWrapper>, TreeCell<ElementWrapper>>()
	    {

		  @Override
		  public TreeCell<ElementWrapper> call(TreeView<ElementWrapper> list)
		  {

			return new WizTreeCell();
		  }
	    });

	    scrollPane.setContent(treeView);

	    scrollPane.setFitToHeight(true);
	    scrollPane.setFitToWidth(true);

	    this.setContent(scrollPane);

      }


      private void onClose(ElementWrapper wrapper)
      {

	    Map<ElementWrapper, Map<ElementWrapper, SimpleStringProperty>> aliases = AppContext.getInstance()
			.getTagNameAliasManager().getNameMap();
	    aliases.put(wrapper, null);
	    aliases.remove(wrapper);

      }


      @Override
      public ElementWrapper getUserData()
      {

	    return (ElementWrapper) super.getUserData();
      }


      public String getName()
      {

	    return getUserData().getElement().getTagName();
      }

}
