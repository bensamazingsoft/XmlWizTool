package com.ben.gui.fx.property.wrapper.impl;

import com.ben.gui.fx.node.FxNode;
import com.ben.gui.fx.property.wrapper.SimplePropertyWrapper;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;

public class SimpleBooleanPropertyWrapper extends SimplePropertyWrapper<SimpleBooleanProperty, Boolean>
		implements FxNode<CheckBox> {

	private final String label;

	public SimpleBooleanPropertyWrapper(SimpleBooleanProperty property) {
		super(property);
		label = "";
	}

	public SimpleBooleanPropertyWrapper(SimpleBooleanProperty property, String label) {
		super(property);
		this.label = label;
	}

	@Override
	public CheckBox getNode() {
		CheckBox cb = new CheckBox();
		cb.setSelected(get());
		cb.selectedProperty().bindBidirectional(property());
		cb.setText(label);
		return cb;
	}

}
