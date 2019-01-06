
package com.ben.xmlwiztool.application.actions.impl;

import java.io.IOException;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.exception.popup.ExceptionPopUp;

public class AppClosingAction implements IAction
{

      @Override
      public void execute()
      {

	    try
	    {

		  AppContext.getInstance().getRecentFiles().save();

		  AppContext.getInstance().getProperties().save();
	    }
	    catch (IOException e)
	    {
		  new ExceptionPopUp(e);
	    }
      }

}
