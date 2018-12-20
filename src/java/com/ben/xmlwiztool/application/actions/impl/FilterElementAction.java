package com.ben.xmlwiztool.application.actions.impl;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.application.executor.Executor;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.processor.impl.ShowElementConditionnalProcessor;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

public class FilterElementAction implements IAction {

	private final String text;

	public FilterElementAction(String text) {
		this.text = text;
	}

	@Override
	public void execute() {

		Executor.getInstance().execute(new HideAllAction());

		ShowElementConditionnalProcessor processor = new ShowElementConditionnalProcessor(text);

		for (ElementWrapper wrapper : GuiFacade.getInstance().getOpenElements()) {
			processor.process(wrapper);
		}

	}

}
