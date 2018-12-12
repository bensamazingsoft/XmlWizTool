package com.ben.xmlwiztool.application.wrapper;

import org.w3c.dom.Element;

import com.ben.xmlwiztool.application.displayable.IDisplayable;
import com.ben.xmlwiztool.gui.viewer.Viewer;

public abstract class ElementWrapper implements IDisplayable {

	protected Element element;
	protected boolean visible, isFold;
	protected ElementWrapper parent;

	public ElementWrapper() {
		super();
		visible = isFold = false;

	}

	public ElementWrapper(Element element) {
		this();
		this.element = element;

	}

	@Override
	public void dispay(Viewer parent) {

	}

	@Override
	public String toString() {
		return "ElementWrapper [element=" + element + ", visible=" + visible + ", isFold=" + isFold + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementWrapper other = (ElementWrapper) obj;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.getTextContent().equals(other.element.getTextContent()))
			return false;
		return true;
	}

	public String getValue() {
		if (element != null) {
			return element.getTextContent();
		}

		return "";
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public ElementWrapper getParent() {
		return parent;
	}

	public void setParent(ElementWrapper parent) {
		this.parent = parent;
	}

}
