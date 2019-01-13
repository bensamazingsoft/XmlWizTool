
package com.ben.xmlwiztool.gui.controls.viewer.treeviewer.tab;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.gui.controls.viewer.sticker.Sticker;
import com.ben.xmlwiztool.gui.controls.viewer.tab.WizTab;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class LeafViewTab extends WizTab
{

      public LeafViewTab(ElementWrapper wrapper)
      {

	    super();

	    getStyleClass().add("LeafViewTab");

	    this.wrapper = wrapper;
	    this.setUserData(wrapper);
	    this.setText("Leaf : " + wrapper.getElement().getTagName());

	    populate();

	    GuiFacade.getInstance().getTabPane().getSelectionModel().select(this);

      }


      @Override
      public void populate()
      {

	    VBox content = new VBox();
	    content.setId("LeafViewTabContent");
	    content.setSpacing(5.0);
	    ScrollPane scrollPane = new ScrollPane();

	    for (ElementWrapper elem : wrapper.getLeaves())
	    {
		  if (show(elem))
		  {
			Sticker sticker = new Sticker(elem);
			content.getChildren().add(sticker);
			sticker.setMinWidth(sticker.computeMaxSize());
			sticker.setMaxWidth(sticker.computeMaxSize());
		  }
	    }

	    scrollPane.setContent(content);

	    scrollPane.setFitToHeight(true);
	    scrollPane.setFitToWidth(true);

	    this.setContent(scrollPane);

      }


      private boolean show(ElementWrapper elem)
      {

	    if (elem.isVisible())
	    {
		  if (elem.isEmpty())
		  {
			return !GuiFacade.getInstance().isHideEmpty();
		  }
		  return true;
	    }

	    return false;
      }

}
