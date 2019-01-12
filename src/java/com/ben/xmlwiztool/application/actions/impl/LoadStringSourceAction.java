package com.ben.xmlwiztool.application.actions.impl;

import org.w3c.dom.Document;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.application.document.exception.DocumentParsingException;
import com.ben.xmlwiztool.application.document.factory.DocumentFactory;
import com.ben.xmlwiztool.application.exception.popup.ExceptionPopUp;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.factory.ElementWrapperFactory;
import com.ben.xmlwiztool.application.wrapper.processor.impl.AliasesProcessor;
import com.ben.xmlwiztool.application.wrapper.processor.impl.SetFilterableProcessor;
import com.ben.xmlwiztool.gui.controls.viewer.tabfactory.TabFactory;
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

			Document doc = DocumentFactory.getDocument(source);

			ElementWrapper wrapper = ElementWrapperFactory.getElementWrapper(doc.getDocumentElement());

			AliasesProcessor processor = new AliasesProcessor(wrapper);
			processor.process(wrapper);

			SetFilterableProcessor filtering = new SetFilterableProcessor();
			filtering.process(wrapper);

			GuiFacade.getInstance().getTabPane().getTabs().add(TabFactory.getTab(wrapper));

		} catch (DocumentParsingException e) {
			new ExceptionPopUp(e);
		}

	}

}
