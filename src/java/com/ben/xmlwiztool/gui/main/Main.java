
package com.ben.xmlwiztool.gui.main;

import java.util.ResourceBundle;

import com.ben.xmlwiztool.application.actions.impl.AppClosingAction;
import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.executor.Executor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{

      private final String IMAGEPATH = "images/";


      public static void main(String[] args) throws Exception
      {

	    launch(args);
      }


      public void start(Stage stage) throws Exception
      {
	    // TODO inquire about memory leak
	    // TODO dress (css) the detailpopup
	    // TODO refactor the alias frmwk
	    // TODO impl recent files menu item (+update via observation)

	    AppContext.init();

	    ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

	    Image appIcon = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "appIcon.png"));

	    String fxmlFile = "/fxml/main.fxml";
	    FXMLLoader loader = new FXMLLoader();
	    Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

	    Scene scene = new Scene(rootNode, 800, 600);
	    scene.getStylesheets().add("/css/styles.css");

	    stage.setOnCloseRequest((event) -> {

		  Executor.getInstance().execute(new AppClosingAction());

	    });

	    stage.setTitle(bundle.getString("appTitle"));
	    stage.getIcons().add(appIcon);
	    stage.setScene(scene);
	    stage.show();
      }

}