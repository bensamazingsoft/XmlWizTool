package com.ben.xmlwiztool.application.wrapper.factory;

import org.w3c.dom.Element;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

public class ElementWrapperFactory {

	private ElementWrapperFactory instance;

	private ElementWrapperFactory() {

	}

	public ElementWrapperFactory getInstance() {

		if (instance == null) {
			instance = new ElementWrapperFactory();
		}

		return instance;

	}

	public ElementWrapper getElementWrapper(Element element) {

		ElementWrapper wrapper;

		// TODO finish this

		return wrapper;
	}

}
