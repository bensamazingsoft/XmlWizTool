package com.ben.xmlwiztool.gui.controls.menu.context.sticker;

import com.ben.gui.fx.property.wrapper.impl.OpenSoloViewAction;
import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.executor.Executor;
import com.ben.xmlwiztool.gui.controls.menu.context.sticker.popup.StickerDetailsPopUp;
import com.ben.xmlwiztool.gui.controls.viewer.sticker.Sticker;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class StickerContextMenu extends ContextMenu {

	private final MenuItem	detailMenuItem;
	private final MenuItem	soloMenuItem;

	public StickerContextMenu(Sticker sticker) {

		detailMenuItem = new MenuItem(AppContext.getInstance().getBundle().getString("stickercontextmenuitem1"));
		soloMenuItem = new MenuItem(AppContext.getInstance().getBundle().getString("stickercontextmenuitem2"));

		detailMenuItem.setOnAction((event) -> {
			new StickerDetailsPopUp(sticker);
		});

		soloMenuItem.setOnAction((event) -> {
			Executor.getInstance().execute(new OpenSoloViewAction(sticker.getWrapper()));
		});

		getItems().addAll(detailMenuItem, soloMenuItem);

	}

}
