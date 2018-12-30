
package com.ben.xmlwiztool.gui.controls.pathbox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class PathBox extends VBox implements Initializable
{

      private ResourceBundle bundle;
      private ElementWrapper wrapper;


      public PathBox()
      {

	    bundle = AppContext.getInstance().getBundle();
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PathBox.fxml"), bundle);
	    fxmlLoader.setRoot(this);
	    fxmlLoader.setController(this);

	    try
	    {
		  fxmlLoader.load();
	    }
	    catch (IOException e)
	    {
		  throw new RuntimeException(e);
	    }
      }


      public PathBox(ElementWrapper wrapper)
      {

	    super();
	    this.wrapper = wrapper;
      }


      @Override
      public void initialize(URL location, ResourceBundle resources)
      {

	    // TODO Auto-generated method stub

      }
}
