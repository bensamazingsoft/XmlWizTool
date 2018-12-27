package com.ben.xmlwiztool.gui.settings.popup;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

public class SettingPopUp extends Alert {

	public SettingPopUp(AlertType arg0) {
		super(arg0);
	}

	public SettingPopUp() {
		super(AlertType.CONFIRMATION);

		initStyle(StageStyle.UTILITY);
		setTitle(AppContext.getInstance().getBundle().getString("settings"));
		setHeaderText("");
		setGraphic(null);

		GridPane content = new GridPane();
		content.setAlignment(Pos.BASELINE_LEFT);

		Label fastLoadLabel = new Label(AppContext.getInstance().getBundle().getString("fastLoad"));
		GridPane.setVgrow(fastLoadLabel, Priority.ALWAYS);
		CheckBox fastLoadCb = new CheckBox();
		GridPane.setVgrow(fastLoadCb, Priority.ALWAYS);
		fastLoadCb.selectedProperty().bindBidirectional(GuiFacade.getInstance().fastLoadProperty());

		content.setMaxWidth(Double.MAX_VALUE);
		content.add(fastLoadLabel, 0, 0);
		content.add(fastLoadCb, 1, 0);

		getDialogPane().setContent(content);

		showAndWait();

	}

}
