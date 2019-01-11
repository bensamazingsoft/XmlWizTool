package com.ben.gui.fx.property.wrapper.impl;

import java.util.Arrays;
import java.util.List;

import com.ben.gui.fx.property.wrapper.SimplePropertyWrapper;
import com.ben.xmlwiztool.gui.facade.GuiFacade;
import com.ben.xmlwiztool.gui.settings.popup.ShowPathSetting;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ShowPathSettingPropertyWrapper
		extends SimplePropertyWrapper<SimpleObjectProperty<ShowPathSetting>, ShowPathSetting> {

	List<Object> values;

	public ShowPathSettingPropertyWrapper(SimpleObjectProperty<ShowPathSetting> property, ShowPathSetting[] values) {
		super(property);
		this.values = Arrays.asList(values);
	}

	public ShowPathSettingPropertyWrapper(SimpleObjectProperty<ShowPathSetting> property, String label,
			ShowPathSetting[] values) {
		super(property, label);
		this.values = Arrays.asList(values);
	}

	@Override
	public ChoiceBox<Object> getNode() {

		HBox hBox = new HBox();
		hBox.setSpacing(5.0);
		hBox.getChildren().add(new Label(label()));

		ChoiceBox<Object> cbox = new ChoiceBox<Object>();
		cbox.setItems(FXCollections.observableArrayList(values));
		hBox.getChildren().add(cbox);

		cbox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			GuiFacade.getInstance().showPathProperty().set((ShowPathSetting) newVal);
		});

		return cbox;
	}

}
