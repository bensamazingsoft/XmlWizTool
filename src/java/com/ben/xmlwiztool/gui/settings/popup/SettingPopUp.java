
package com.ben.xmlwiztool.gui.settings.popup;

import com.ben.xmlwiztool.application.context.AppContext;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

public class SettingPopUp extends Alert
{

      public SettingPopUp(AlertType arg0)
      {

	    super(arg0);
      }


      public SettingPopUp()
      {

	    super(AlertType.CONFIRMATION);

	    initStyle(StageStyle.UTILITY);
	    setTitle(AppContext.getInstance().getBundle().getString("settings"));
	    setHeaderText("");
	    setGraphic(null);

	    GridPane content = new GridPane();
	    content.setAlignment(Pos.BASELINE_LEFT);

	    // Label treeViewLabel = new Label(AppContext.getInstance().getBundle().getString("treeView") + " ");
	    // GridPane.setVgrow(treeViewLabel, Priority.ALWAYS);
	    // CheckBox treeViewCb = new CheckBox();
	    // GridPane.setVgrow(treeViewCb, Priority.ALWAYS);
	    // treeViewCb.selectedProperty().bindBidirectional(GuiFacade.getInstance().treeViewProperty());

	    content.setMaxWidth(Double.MAX_VALUE);

	    // content.add(treeViewLabel, 0, 1);

	    // content.add(treeViewCb, 1, 1);

	    getDialogPane().setContent(content);

	    showAndWait();

      }

}
