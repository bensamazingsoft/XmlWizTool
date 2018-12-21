package com.ben.xmlwiztool.application.actions.impl;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.application.executor.Executor;

public class LoadClipBoardAction implements IAction {

	@Override
	public void execute() {

		String source = getString();

		Executor.getInstance().execute(new LoadStringSourceAction(source));

	}

	private String getString() {

		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		String trimed = "";

		try {
			if (c.getContents(null).isDataFlavorSupported(DataFlavor.stringFlavor)) {

				trimed = ((String) (c.getData((DataFlavor.stringFlavor)))).trim();

			}
		} catch (Exception e) {
		}
		return trimed;
	}

}
