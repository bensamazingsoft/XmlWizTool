package com.ben.xmlwiztool.gui.main;

import java.net.URL;
import java.util.ResourceBundle;

import com.ben.xmlwiztool.gui.controls.toolbar.ButBar;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

public class MainController implements Initializable {

	@FXML
	ButBar butBar;

	@FXML
	TabPane tabPane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		GuiFacade.getInstance().setTabPane(tabPane);

	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

}
