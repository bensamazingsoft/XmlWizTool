package com.ben.xmlwiztool.application.actions.impl;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;

import org.w3c.dom.Document;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.application.document.exception.DocumentParsingException;
import com.ben.xmlwiztool.application.document.factory.DocumentFactory;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.factory.ElementWrapperFactory;
import com.ben.xmlwiztool.gui.controls.tab.WizTab;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

public class LoadClipBoardAction implements IAction {

	@Override
	public void execute() {
		try {

			String source = getString();

			Document doc = DocumentFactory.getDocument(source);

			ElementWrapper wrapper = ElementWrapperFactory.getElementWrapper(doc.getDocumentElement());

			GuiFacade.getInstance().getTabPane().getTabs().add(new WizTab(wrapper));

		} catch (DocumentParsingException e) {
			// TODO handle action fail

		}

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
