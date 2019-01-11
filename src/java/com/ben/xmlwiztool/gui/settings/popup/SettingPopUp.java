
package com.ben.xmlwiztool.gui.settings.popup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ben.gui.fx.property.wrapper.SimplePropertyWrapper;
import com.ben.gui.fx.property.wrapper.impl.ShowPathSettingPropertyWrapper;
import com.ben.xmlwiztool.application.actions.impl.RefreshTabsAction;
import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.executor.Executor;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;

public class SettingPopUp extends Alert {

	@SuppressWarnings("rawtypes")
	private List<SimplePropertyWrapper> propWrappers = new ArrayList<>();

	public SettingPopUp(AlertType arg0) {

		super(arg0);
	}

	public SettingPopUp() {

		super(AlertType.CONFIRMATION);

		propWrappers.add(
				new ShowPathSettingPropertyWrapper(GuiFacade.getInstance().showPathProperty(), ShowPathSetting.values()));

		initStyle(StageStyle.UTILITY);
		setTitle(AppContext.getInstance().getBundle().getString("settings"));
		setHeaderText("");
		setGraphic(null);

		HBox content = new HBox();

		content.getChildren().addAll(getNodes(propWrappers));

		getDialogPane().setContent(content);

		showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				Executor.getInstance().execute(new RefreshTabsAction());
			} else {
				propWrappers.forEach(prop -> prop.reinit());
			}
		});

	}

	@SuppressWarnings("rawtypes")
	private List<Node> getNodes(List<SimplePropertyWrapper> propWrappers) {

		return propWrappers.stream().map(prop -> prop.getNode()).collect(Collectors.toList());
	}

}
