
package com.ben.xmlwiztool.gui.treeviewer.item;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.ComplexElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.SimpleElementWrapper;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class WrapperTreeItem extends TreeItem<ElementWrapper>
{

      private final ElementWrapper wrapper;
      private boolean		   load;


      public WrapperTreeItem(ElementWrapper wrapper)
      {

	    super();

	    load = true;
	    this.wrapper = wrapper;
	    this.setValue(wrapper);
	    this.expandedProperty().bindBidirectional(wrapper.expandProperty());
      }


      @Override
      public ObservableList<TreeItem<ElementWrapper>> getChildren()
      {

	    if (load)
	    {
		  if (wrapper instanceof ComplexElementWrapper)
		  {

			ComplexElementWrapper complex = (ComplexElementWrapper) wrapper;

			if (!complex.getChildren().isEmpty())
			{

			      super.getChildren().setAll(makeChilds(complex));

			}
		  }
		  load = false;
	    }
	    return super.getChildren();
      }


      private ObservableList<TreeItem<ElementWrapper>> makeChilds(ComplexElementWrapper complex)
      {

	    ObservableList<TreeItem<ElementWrapper>> children = FXCollections.observableArrayList();

	    for (ElementWrapper child : complex.getChildren())
	    {

		  if (GuiFacade.getInstance().isHideEmpty() && child.isEmpty())
		  {
			continue;
		  }

		  if (child.isVisible())
		  {
			children.add(new WrapperTreeItem(child));
		  }

	    }

	    return children;
      }


      @Override
      public boolean isLeaf()
      {

	    return wrapper instanceof SimpleElementWrapper;
      }

}
