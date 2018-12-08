package com.ben.xmlwiztool.application.wrapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

public class ComplexElementWrapper extends ElementWrapper {

	private List<ElementWrapper> children = new ArrayList<>();

	// is the corresponding element a collection entity ?
	boolean isCollection = false;

	public ComplexElementWrapper(Element element) {
		super(element);
		// TODO ComplexElementWrapper

		if (element.hasChildNodes()) {
			for (int i = 0; i < element.getChildNodes().getLength(); i++) {

			}
		}

	}

	public List<ElementWrapper> getChildren() {
		return children;
	}

	public void setChildren(List<ElementWrapper> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "ComplexElementWrapper (element = " + element + "[children=" + children + ", isCollection="
				+ isCollection + "]";
	}

}
