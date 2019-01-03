package com.ben.xmlwiztool.gui.controls.viewer.treeviewer.cell;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.gui.controls.viewer.sticker.Sticker;

import javafx.scene.control.TreeCell;

public class WizTreeCell extends TreeCell<ElementWrapper> {

	public WizTreeCell() {
	}

	@Override
	protected void updateItem(ElementWrapper wrapper, boolean empty) {
		super.updateItem(wrapper, empty);

		if (wrapper != null && !empty) {

			Sticker sticker = new Sticker(getItem());

			double maxCharSize = sticker.computeMaxSize();

			sticker.setMinWidth(maxCharSize);
			sticker.setMaxWidth(maxCharSize);

			setGraphic(sticker);

		} else {
			setGraphic(null);
			setText(null);
		}

	}

}
