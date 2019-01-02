
package com.ben.xmlwiztool.gui.controls.menu.context.sticker.popup;

import com.ben.xmlwiztool.gui.controls.viewer.sticker.Sticker;
import com.ben.xmlwiztool.gui.controls.viewer.sticker.details.pane.StickerDetailPane;

import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;

public class StickerDetailsPopUp extends Alert {

	public StickerDetailsPopUp(Sticker sticker) {

		super(AlertType.CONFIRMATION);

		setTitle("<" + sticker.getWrapper().getElement().getTagName() + ">");
		setHeaderText("");
		setGraphic(null);

		ScrollPane sp = new ScrollPane();
		sp.getStylesheets().add("/css/styles.css");
		sp.getStyleClass().add("StickerDetailPane");
		sp.setContent(new StickerDetailPane(sticker));
		sp.setFitToHeight(true);
		sp.setFitToWidth(true);

		getDialogPane().setContent(sp);

		initModality(Modality.NONE);
		show();
	}

}
