package com.ben.xmlwiztool.application.actions.impl;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.processor.impl.FilterElementProcessor;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

public class FilterElementAction implements IAction {

	private final String text;

	public FilterElementAction(String text) {
		this.text = text;
	}

	@Override
	public void execute() {

		FilterElementProcessor processor = new FilterElementProcessor(text);

		for (ElementWrapper wrapper : GuiFacade.getInstance().getOpenElements()) {
			processor.process(wrapper);
		}

	}

}
