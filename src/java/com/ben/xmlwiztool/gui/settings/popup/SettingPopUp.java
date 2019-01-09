
package com.ben.xmlwiztool.gui.settings.popup;

import com.ben.xmlwiztool.application.actions.impl.RefreshTabsAction;
import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.executor.Executor;
import com.ben.xmlwiztool.application.restorable.Restorable;
import com.ben.xmlwiztool.gui.controls.settings.factory.SettingcontrolFactory;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
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
		content.setMaxWidth(Double.MAX_VALUE);

		Restorable<SimpleObjectProperty<ShowPathSetting>, ShowPathSetting> showPathProp = new Restorable<>(
				GuiFacade.getInstance().showPathProperty());
		Restorable.Memento<ShowPathSetting> showPathMemento = showPathProp.save();

		content.add(SettingcontrolFactory.getcontrol(showPathProp));

		getDialogPane().setContent(content);

		showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				Executor.getInstance().execute(new RefreshTabsAction());
			} else {
				showPathProp.restore(showPathMemento);
			}
		});

	}

}
