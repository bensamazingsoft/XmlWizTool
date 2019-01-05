
package com.ben.xmlwiztool.gui.controls.viewer.treeviewer.tab;

import java.util.Map;

import com.ben.xmlwiztool.application.actions.impl.ResetChangedObservablesAction;
import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.executor.Executor;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.gui.controls.viewer.treeviewer.cell.WizTreeCell;
import com.ben.xmlwiztool.gui.facade.GuiFacade;
import com.ben.xmlwiztool.gui.treeviewer.item.WrapperTreeItem;
import com.ben.xmlwiztool.observable.Observer;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

public class TreeViewTab extends Tab implements Observer
{

      private ScrollPane     scrollPane	= new ScrollPane();
      private ElementWrapper wrapper;


      public TreeViewTab(ElementWrapper wrapper)
      {

	    super();

	    this.wrapper = wrapper;
	    wrapper.addObserver(this);

	    this.setUserData(wrapper);
	    this.setText(wrapper.getElement().getTagName());
	    this.setOnClosed((event) -> {
		  onClose(wrapper);
	    });

	    populate();

	    GuiFacade.getInstance().getTabPane().getSelectionModel().select(this);

      }


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


      @Override
      public void update()
      {

	    populate();
	    Executor.getInstance().execute(new ResetChangedObservablesAction(wrapper));

      }
}
