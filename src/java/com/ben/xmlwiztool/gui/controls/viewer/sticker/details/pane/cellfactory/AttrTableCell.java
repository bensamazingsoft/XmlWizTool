
package com.ben.xmlwiztool.gui.controls.viewer.sticker.details.pane.cellfactory;

import com.ben.xmlwiztool.application.wrapper.AttributeWrapper;

import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

public class AttrTableCell extends TableCell<AttributeWrapper, String> {

	@Override
	protected void updateItem(String value, boolean empty) {

		super.updateItem(value, empty);

		StackPane content = new StackPane();
		TextArea textArea = new TextArea(value);
		content.getChildren().add(textArea);
		textArea.setWrapText(true);
		textArea.setEditable(false);
		textArea.setPrefRowCount(1);
		setGraphic(content);

	}

}
