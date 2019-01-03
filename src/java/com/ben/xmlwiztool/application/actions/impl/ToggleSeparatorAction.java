package com.ben.xmlwiztool.application.actions.impl;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

public class ToggleSeparatorAction implements IAction {

	@Override
	public void execute() {
		GuiFacade.getInstance().toggleSeparator();

	}

}
