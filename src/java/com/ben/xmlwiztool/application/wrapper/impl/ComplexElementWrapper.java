
package com.ben.xmlwiztool.application.wrapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.factory.ElementWrapperFactory;

public class ComplexElementWrapper extends ElementWrapper
{

      private List<ElementWrapper> children = new ArrayList<>();


      public ComplexElementWrapper(Element element)
      {

	    super(element);

	    for (int i = 0; i < element.getChildNodes().getLength(); i++)
	    {

		  if (element.getChildNodes().item(i) instanceof Element)
		  {

			// recursive instantiation to mirror the element child tree
			Element newElem = (Element) element.getChildNodes().item(i);
			ElementWrapper newWrapper = ElementWrapperFactory.getElementWrapper(newElem);
			newWrapper.setParent(this);
			children.add(newWrapper);

			newWrapper.addObserver(this);

		  }

	    }

      }


      public List<ElementWrapper> getChildren()
      {

	    return children;
      }


      public void setChildren(List<ElementWrapper> children)
      {

	    this.children = children;
      }

}
