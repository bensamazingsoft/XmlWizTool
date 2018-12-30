
package com.ben.xmlwiztool.gui.controls.viewer.sticker.details.pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.gui.controls.pathbox.PathBox;
import com.ben.xmlwiztool.gui.controls.viewer.sticker.Sticker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StickerDetailPane extends VBox implements Initializable
{

      private Sticker	     sticker;
      private ResourceBundle bundle;

      @FXML
      Text		     rootName;

      @FXML
      Text		     tagname;

      @FXML
      TableView		     attrTable;

      @FXML
      TextArea		     valueTextArea;

      @FXML
      PathBox		     pathBox;


      public StickerDetailPane()
      {

	    bundle = AppContext.getInstance().getBundle();
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/StickerDetailPane.fxml"), bundle);
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


      public StickerDetailPane(Sticker sticker)
      {

	    this.sticker = sticker;

      }


      @Override
      public void initialize(URL arg0, ResourceBundle arg1)
      {

	    // TODO Auto-generated method stub

      }

}
