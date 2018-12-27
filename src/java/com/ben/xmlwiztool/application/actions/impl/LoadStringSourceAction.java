package com.ben.xmlwiztool.application.actions.impl;

import org.w3c.dom.Document;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.application.document.exception.DocumentParsingException;
import com.ben.xmlwiztool.application.document.factory.DocumentFactory;
import com.ben.xmlwiztool.application.exception.popup.ExceptionPopUp;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.factory.ElementWrapperFactory;
import com.ben.xmlwiztool.application.wrapper.processor.SetFilterableProcessor;
import com.ben.xmlwiztool.application.wrapper.processor.impl.AliasesProcessor;
import com.ben.xmlwiztool.gui.controls.tab.WizTab;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

public class LoadStringSourceAction implements IAction {

	private String source;

	public LoadStringSourceAction(String source) {
		super();
		this.source = source;
	}

	@Override
	public void execute() {

		try {
			Long before = System.currentTimeMillis();

			Document doc = DocumentFactory.getDocument(source);

			ElementWrapper wrapper = ElementWrapperFactory.getElementWrapper(doc.getDocumentElement());

			Long docu = System.currentTimeMillis();
			System.out.println("Document made in " + (docu - before) + "ms");

			AliasesProcessor processor = new AliasesProcessor(wrapper);
			processor.process(wrapper);

			Long alias = System.currentTimeMillis();
			System.out.println("Aliases made in " + (alias - docu) + "ms");

			SetFilterableProcessor filtering = new SetFilterableProcessor();
			filtering.process(wrapper);

			Long filter = System.currentTimeMillis();
			System.out.println("Filtering made in " + (filter - alias) + "ms");

			GuiFacade.getInstance().getTabPane().getTabs().add(new WizTab(wrapper));

			Long after = System.currentTimeMillis();

			System.out.println("Tab made in " + (after - alias) + "ms");
			System.out.println("loaded in : " + (after - before) + " ms");

		} catch (DocumentParsingException e) {
			new ExceptionPopUp(e);
		}

	}

}
