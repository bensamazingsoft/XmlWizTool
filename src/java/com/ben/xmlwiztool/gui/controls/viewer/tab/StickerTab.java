
package com.ben.xmlwiztool.gui.controls.viewer.tab;

import java.util.Map;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.gui.controls.viewer.ElementViewer;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;

public class StickerTab extends Tab {

	ScrollPane scrollPane = new ScrollPane();

	public StickerTab(ElementWrapper wrapper) {
		super();

		Long start = System.currentTimeMillis();

		this.setUserData(wrapper);
		this.setText(wrapper.getElement().getTagName());
		this.setOnClosed((event) -> {
			onClose(wrapper);
		});

		scrollPane.setContent(new ElementViewer(wrapper));

		this.setContent(scrollPane);

		GuiFacade.getInstance().getTabPane().getSelectionModel().select(this);

		System.out.println("wiztab loaded in : " + (System.currentTimeMillis() - start) + "ms");

	}

	private void onClose(ElementWrapper wrapper) {

		Map<ElementWrapper, Map<ElementWrapper, String>> aliases = AppContext.getInstance().getTagNameAliasManager()
				.getNameMap();
		aliases.remove(wrapper);

	}

	@Override
	public ElementWrapper getUserData() {

		return (ElementWrapper) super.getUserData();
	}

	public String getName() {

		return getUserData().getElement().getTagName();
	}

}
