
package com.ben.xmlwiztool.gui.controls.menu.bar;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.xmlwiztool.application.actions.impl.FoldAllAction;
import com.ben.xmlwiztool.application.actions.impl.LoadClipBoardAction;
import com.ben.xmlwiztool.application.actions.impl.LoadFileAction;
import com.ben.xmlwiztool.application.actions.impl.ShowAllAction;
import com.ben.xmlwiztool.application.actions.impl.ToggleHideEmptyAction;
import com.ben.xmlwiztool.application.actions.impl.ToggleSeparatorAction;
import com.ben.xmlwiztool.application.actions.impl.UnFoldAllAction;
import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.executor.Executor;
import com.ben.xmlwiztool.gui.controls.alias.manager.popup.ManageAliasPopUp;
import com.ben.xmlwiztool.gui.controls.menu.menuitem.RecentFileMenuItem;
import com.ben.xmlwiztool.gui.settings.popup.SettingPopUp;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class WizMenuBar extends MenuBar implements Initializable {

	private ResourceBundle bundle;

	public WizMenuBar() {

		bundle = AppContext.getInstance().getBundle();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/WizMenuBar.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@FXML
	Menu fileMenu;

	@FXML
	public void handleOpenAction(Event event) {

		openAction();
	}

	private void openAction() {

		Executor.getInstance().execute(new LoadFileAction());

	}

	@FXML
	public void handleloadClipBoardAction(Event event) {

		loadClipBoardAction();
	}

	private void loadClipBoardAction() {

		Executor.getInstance().execute(new LoadClipBoardAction());

	}

	@FXML
	public void handleShowAllAction(Event event) {

		showAllAction();
	}

	private void showAllAction() {

		Executor.getInstance().execute(new ShowAllAction());

	}

	@FXML
	public void handleFoldAllAction(Event event) {

		foldAllAction();
	}

	private void foldAllAction() {

		Executor.getInstance().execute(new FoldAllAction());

	}

	@FXML
	public void handleUnFoldAllAction(Event event) {

		unFoldAllAction();
	}

	private void unFoldAllAction() {

		Executor.getInstance().execute(new UnFoldAllAction());

	}

	@FXML
	public void handleToggleSeparatorAction(Event event) {

		toggleSeparatorAction();
	}

	private void toggleSeparatorAction() {

		Executor.getInstance().execute(new ToggleSeparatorAction());

	}

	@FXML
	public void handleManageAliasesAction(Event event) {

		manageAliasesAction();
	}

	private void manageAliasesAction() {

		new ManageAliasPopUp();

	}

	@FXML
	public void handleHideEmptyAction(Event event) {

		hideEmptyAction();
	}

	private void hideEmptyAction() {

		Executor.getInstance().execute(new ToggleHideEmptyAction());

	}

	@FXML
	public void handleSettingsAction(Event event) {

		settingsAction();
	}

	private void settingsAction() {

		new SettingPopUp();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		addRecentFilesMenuItem();
	}

	private void addRecentFilesMenuItem() {

		fileMenu.getItems().removeIf(menuItem -> menuItem instanceof RecentFileMenuItem);

		for (File file : AppContext.getInstance().getRecentFiles().getFiles()) {
			fileMenu.getItems().add(new RecentFileMenuItem(file));
		}

	}

}
