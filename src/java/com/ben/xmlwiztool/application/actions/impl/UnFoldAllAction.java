package com.ben.xmlwiztool.application.actions.impl;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.processor.WrapperProcessor;
import com.ben.xmlwiztool.application.wrapper.processor.impl.UnFoldElementProcessor;

public class UnFoldAllAction implements IAction {

	@Override
	public void execute() {
		for (ElementWrapper elem : AppContext.openDocuments()) {

			WrapperProcessor processor = new UnFoldElementProcessor();
			processor.process(elem);

		}

	}

}
