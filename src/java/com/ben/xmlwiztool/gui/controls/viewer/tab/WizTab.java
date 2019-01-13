
package com.ben.xmlwiztool.gui.controls.viewer.tab;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

import javafx.scene.control.Tab;

public abstract class WizTab extends Tab
{

      protected ElementWrapper wrapper;


      public WizTab()
      {

	    super();
	    getStyleClass().add("WizTab");
      }


      public abstract void populate();

}
