package com.ben.xmlwiztool.gui.main;

import java.io.IOException;
import java.util.ResourceBundle;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.exception.popup.ExceptionPopUp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) throws Exception {

		launch(args);
	}

	public void start(Stage stage) throws Exception {

		// TODO set app icons
		// TODO inquire about memory leak
		// TODO dress (css) the detailpopup
		// TODO refactor the alias frmwk
		// TODO implement tooltips

		AppContext.init();

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		String fxmlFile = "/fxml/main.fxml";
		FXMLLoader loader = new FXMLLoader();
		Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

		Scene scene = new Scene(rootNode, 800, 600);
		scene.getStylesheets().add("/css/styles.css");

		stage.setOnCloseRequest((event) -> {
			try {
				AppContext.getInstance().getProperties().save();
			} catch (IOException e) {
				new ExceptionPopUp(e);
			}
		});

		stage.setTitle(bundle.getString("appTitle"));
		stage.setScene(scene);
		stage.show();
	}

}