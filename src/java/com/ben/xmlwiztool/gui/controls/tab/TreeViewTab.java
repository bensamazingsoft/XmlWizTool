package com.ben.xmlwiztool.gui.controls.tab;

import java.util.Map;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.gui.facade.GuiFacade;
import com.ben.xmlwiztool.gui.treeviewer.cell.WizTreeCell;
import com.ben.xmlwiztool.gui.treeviewer.item.WrapperTreeItem;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

public class TreeViewTab extends Tab {

	ScrollPane scrollPane = new ScrollPane();

	public TreeViewTab(ElementWrapper wrapper) {
		super();

		this.setUserData(wrapper);
		this.setText(wrapper.getElement().getTagName());
		this.setOnClosed((event) -> {
			onClose(wrapper);
		});

		TreeView<ElementWrapper> treeView = new TreeView<ElementWrapper>(new WrapperTreeItem(wrapper));
		treeView.setCellFactory(new Callback<TreeView<ElementWrapper>, TreeCell<ElementWrapper>>() {
			@Override
			public TreeCell<ElementWrapper> call(TreeView<ElementWrapper> list) {
				return new WizTreeCell();
			}
		});

		scrollPane.setContent(treeView);

		this.setContent(scrollPane);

		GuiFacade.getInstance().getTabPane().getSelectionModel().select(this);

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
