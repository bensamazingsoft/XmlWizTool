
package com.ben.xmlwiztool.application.wrapper.factory;

import org.w3c.dom.Element;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.ComplexElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.SimpleElementWrapper;

public class ElementWrapperFactory {

	public static ElementWrapper getElementWrapper(Element element) {

		if (element.hasChildNodes()) {

			for (int i = 0; i < element.getChildNodes().getLength(); i++) {

				if (element.getChildNodes().item(i) instanceof Element) {

					return new ComplexElementWrapper(element);
				}
			}
		}

		return new SimpleElementWrapper(element);

	}

}
