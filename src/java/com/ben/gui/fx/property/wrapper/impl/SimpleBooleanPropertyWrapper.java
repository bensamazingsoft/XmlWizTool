package com.ben.gui.fx.property.wrapper.impl;

import com.ben.gui.fx.property.wrapper.SimplePropertyWrapper;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;

public class SimpleBooleanPropertyWrapper extends SimplePropertyWrapper<SimpleBooleanProperty, Boolean> {

	public SimpleBooleanPropertyWrapper(SimpleBooleanProperty property) {
		super(property);

	}

	public SimpleBooleanPropertyWrapper(SimpleBooleanProperty property, String label) {
		super(property, label);

	}

	@Override
	public CheckBox getNode() {
		CheckBox cb = new CheckBox();
		cb.setSelected(get());
		cb.selectedProperty().bindBidirectional(property());
		cb.setText(label());
		return cb;
	}

}
