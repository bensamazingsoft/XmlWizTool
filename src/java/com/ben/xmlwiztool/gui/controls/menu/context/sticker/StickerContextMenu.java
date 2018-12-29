package com.ben.xmlwiztool.gui.controls.menu.context.sticker;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.gui.controls.menu.context.sticker.popup.StickerDetailsPopUp;
import com.ben.xmlwiztool.gui.controls.viewer.sticker.Sticker;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class StickerContextMenu extends ContextMenu {

	MenuItem detailMenuItem;

	public StickerContextMenu(Sticker sticker) {

		detailMenuItem = new MenuItem(AppContext.getInstance().getBundle().getString("stickercontextmenuitem1"));
		detailMenuItem.setOnAction((event) -> {
			new StickerDetailsPopUp(sticker);
		});

		getItems().add(detailMenuItem);

	}

}
