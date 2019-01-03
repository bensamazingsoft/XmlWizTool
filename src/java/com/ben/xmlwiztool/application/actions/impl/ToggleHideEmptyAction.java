package com.ben.xmlwiztool.application.actions.impl;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

public class ToggleHideEmptyAction implements IAction {

	@Override
	public void execute() {
		boolean state = GuiFacade.getInstance().isHideEmpty();
		GuiFacade.getInstance().setHideEmpty(!state);

	}

}
