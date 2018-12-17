
package com.ben.xmlwiztool.gui.controls.toolbar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.xmlwiztool.application.actions.impl.FoldAllAction;
import com.ben.xmlwiztool.application.actions.impl.LoadClipBoardAction;
import com.ben.xmlwiztool.application.actions.impl.ShowAllAction;
import com.ben.xmlwiztool.application.actions.impl.UnFoldAllAction;
import com.ben.xmlwiztool.application.executor.Executor;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButBar extends ToolBar implements Initializable {

	private final String IMAGEPATH = "images/buttons/";

	@FXML
	private ToggleButton settingsBut;

	@FXML
	private ToggleButton loadClipBoardBut;

	@FXML
	private ToggleButton loadFileBut;

	@FXML
	private ToggleButton showAllBut;

	@FXML
	private ToggleButton unFoldAllBut;

	@FXML
	private ToggleButton foldAllBut;

	@FXML
	private ToggleButton toggleSeparatorBut;

	@FXML
	private ToggleButton manageNamesBut;

	// get images
	final private Image settingsButImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "settingsBut.png"));
	final private ImageView settingsButImgView = new ImageView();

	final private Image loadClipBoardButImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "loadClipBoardBut.png"));
	final private ImageView loadClipBoardButImgView = new ImageView();

	final private Image loadFileImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "loadFileBut.png"));
	final private ImageView loadFileImgView = new ImageView();

	final private Image unFoldAllImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "unFoldAll.png"));
	final private ImageView unFoldAllImgView = new ImageView();

	final private Image foldAllButImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "foldAllBut.png"));
	final private ImageView foldAllButImgView = new ImageView();

	final private Image manageNamesButImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "manageNamesBut.png"));
	final private ImageView manageNamesButImgView = new ImageView();

	final private Image showAllButImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "showAllBut.png"));
	final private ImageView showAllButImgView = new ImageView();

	final private Image toggleSeparatorButSelectedImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "toggleSeparatorButSelected.png"));
	final private Image toggleSeparatorButUnSelectedImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "toggleSeparatorButUnSelected.png"));
	final private ImageView toggleSeparatorButImgView = new ImageView();

	private String separator = ".";

	public ButBar() {

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ButBar.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	private void actionSettings(ActionEvent event) {

		handleActionSettings();
	}

	private void handleActionSettings() {
		// TODO handleActionSettings()

	}

	@FXML
	private void actionLoadClipBoard(ActionEvent event) {

		handleActionLoadClipBoard();
	}

	private void handleActionLoadClipBoard() {
		// TODO handleActionLoadClipBoard()
		Executor.getInstance().execute(new LoadClipBoardAction());

	}

	@FXML
	private void actionLoadFile(ActionEvent event) {

		handleActionloadFile();
	}

	private void handleActionloadFile() {
		// TODO handleActionloadFile()

	}

	@FXML
	private void actionUnFoldAll(ActionEvent event) {

		handleActionUnFoldAll();
	}

	private void handleActionUnFoldAll() {

		Executor.getInstance().execute(new UnFoldAllAction());

	}

	@FXML
	private void actionFoldAll(ActionEvent event) {

		handleActionFoldAll();
	}

	private void handleActionFoldAll() {

		Executor.getInstance().execute(new FoldAllAction());

	}

	@FXML
	private void actionToggleSeparator(ActionEvent event) {

		handleActionToggleSeparator();
	}

	private void handleActionToggleSeparator() {
		GuiFacade.getInstance().toggleSeparator();

	}

	@FXML
	private void actionManageNames(ActionEvent event) {

		handleActionManageNames();
	}

	private void handleActionManageNames() {
		// TODO handleActionManageNames()

	}

	@FXML
	private void actionShowAll(ActionEvent event) {

		handleActionShowAll();
	}

	private void handleActionShowAll() {

		Executor.getInstance().execute(new ShowAllAction());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		settingsBut.setGraphic(settingsButImgView);
		settingsButImgView.imageProperty().set(settingsButImg);

		loadClipBoardBut.setGraphic(loadClipBoardButImgView);
		loadClipBoardButImgView.imageProperty().set(loadClipBoardButImg);

		loadFileBut.setGraphic(loadFileImgView);
		loadFileImgView.imageProperty().set(loadFileImg);

		unFoldAllBut.setGraphic(unFoldAllImgView);
		unFoldAllImgView.imageProperty().set(unFoldAllImg);

		foldAllBut.setGraphic(foldAllButImgView);
		foldAllButImgView.imageProperty().set(foldAllButImg);

		showAllBut.setGraphic(showAllButImgView);
		showAllButImgView.imageProperty().set(showAllButImg);

		toggleSeparatorBut.setGraphic(toggleSeparatorButImgView);
		toggleSeparatorBut.setUserData(separator);
		toggleSeparatorButImgView.imageProperty().bind(Bindings.when(toggleSeparatorBut.selectedProperty())
				.then(toggleSeparatorButSelectedImg).otherwise(toggleSeparatorButUnSelectedImg));

		manageNamesBut.setGraphic(manageNamesButImgView);
		manageNamesButImgView.imageProperty().set(manageNamesButImg);

	}

}
