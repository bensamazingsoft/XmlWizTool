
package com.ben.xmlwiztool.application.wrapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Element;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

public class SimpleElementWrapper extends ElementWrapper
{

      public SimpleElementWrapper(Element element)
      {

	    super(element);

	    if (getValue().length() > 0)
	    {
		  setEmpty(false);
	    }

      }


      @Override
      public Set<ElementWrapper> getLeaves()
      {

	    Set<ElementWrapper> leaves = new HashSet<>();
	    leaves.add(this);
	    return leaves;
      }

}
