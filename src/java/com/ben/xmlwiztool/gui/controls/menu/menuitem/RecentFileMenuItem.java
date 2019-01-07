
package com.ben.xmlwiztool.gui.controls.menu.menuitem;

import java.io.File;

import com.ben.xmlwiztool.application.actions.impl.LoadFileAction;
import com.ben.xmlwiztool.application.executor.Executor;

import javafx.scene.control.MenuItem;

public class RecentFileMenuItem extends MenuItem {

	private final File file;

	public RecentFileMenuItem(File file) {
		this.file = file;
		setText(file.toString());
		setOnAction((Event) -> handleAction());
	}

	private void handleAction() {
		Executor.getInstance().execute(new LoadFileAction(file));

	}

}
