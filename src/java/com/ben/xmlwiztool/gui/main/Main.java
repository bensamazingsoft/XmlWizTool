package com.ben.xmlwiztool.gui.main;

import java.util.ResourceBundle;

import com.ben.xmlwiztool.application.context.AppContext;

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

		AppContext.init();

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		String fxmlFile = "/fxml/main.fxml";
		FXMLLoader loader = new FXMLLoader();
		Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

		Scene scene = new Scene(rootNode, 400, 200);
		scene.getStylesheets().add("/css/styles.css");

		stage.setTitle(bundle.getString("appTitle"));
		stage.setScene(scene);
		stage.show();
	}

}