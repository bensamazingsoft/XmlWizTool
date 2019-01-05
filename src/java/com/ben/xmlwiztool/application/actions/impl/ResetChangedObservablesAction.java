
package com.ben.xmlwiztool.application.actions.impl;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.processor.impl.ResetChangedObservablesProcessor;

public class ResetChangedObservablesAction implements IAction
{

      private ElementWrapper wrapper;


      public ResetChangedObservablesAction(ElementWrapper wrapper)
      {

	    this.wrapper = wrapper;
      }


      @Override
      public void execute()
      {

	    ResetChangedObservablesProcessor processor = new ResetChangedObservablesProcessor();
	    processor.process(wrapper);
      }

}
