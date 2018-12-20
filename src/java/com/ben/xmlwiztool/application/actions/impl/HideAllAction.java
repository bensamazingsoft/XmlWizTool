package com.ben.xmlwiztool.application.actions.impl;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.processor.WrapperProcessor;
import com.ben.xmlwiztool.application.wrapper.processor.impl.HideElementProcessor;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

public class HideAllAction implements IAction {

	@Override
	public void execute() {
		for (ElementWrapper elem : GuiFacade.getInstance().getOpenElements()) {

			WrapperProcessor processor = new HideElementProcessor();
			processor.process(elem);

		}

	}

}
