
package com.ben.xmlwiztool.application.wrapper.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Element;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.factory.ElementWrapperFactory;

public class ComplexElementWrapper extends ElementWrapper
{

      private List<ElementWrapper> children	= new ArrayList<>();

      // is the corresponding element a collection entity ?
      boolean			   isCollection	= false;


      public ComplexElementWrapper(Element element)
      {

	    super(element);

	    for (int i = 0; i < element.getChildNodes().getLength(); i++)
	    {

		  Set<String> names = new HashSet<>();

		  if (element.getChildNodes().item(i) instanceof Element)
		  {

			// recursive instantiation to mirror the element child tree
			Element newElem = (Element) element.getChildNodes().item(i);
			ElementWrapper newWrapper = ElementWrapperFactory.getElementWrapper(newElem);
			newWrapper.setParent(this);
			children.add(newWrapper);

			names.add(newElem.getTagName());
		  }

		  // if all child tags are the same it's a collection
		  isCollection = names.size() == 1 ? true : false;

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


      @Override
      public void dispay()
      {

	    // TODO dispay
	    super.dispay();
      }


      @Override
      public String toString()
      {

	    return "ComplexElementWrapper (element = " + element + "[children=" + children + ", isCollection="
			+ isCollection + "]";
      }

}
