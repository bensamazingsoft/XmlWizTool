package com.ben.xmlwiztool.application.wrapper;

import org.w3c.dom.Element;

import javafx.beans.property.SimpleBooleanProperty;

public abstract class ElementWrapper {

	protected Element element;
	protected SimpleBooleanProperty visible, fold;
	protected ElementWrapper parent;

	public ElementWrapper() {
		super();

		visible = new SimpleBooleanProperty();
		fold = new SimpleBooleanProperty();

		setFold(false);
		setVisible(false);

	}

	public ElementWrapper(Element element) {
		this();
		this.element = element;

	}

	@Override
	public String toString() {
		return "ElementWrapper [element=" + element + ", visible=" + visible + ", fold=" + fold + ", parent=" + parent
				+ "]";
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

	public final SimpleBooleanProperty visibleProperty() {
		return this.visible;
	}

	public final boolean isVisible() {
		return this.visibleProperty().get();
	}

	public final void setVisible(final boolean visible) {
		this.visibleProperty().set(visible);
	}

	public final SimpleBooleanProperty foldProperty() {
		return this.fold;
	}

	public final boolean isFold() {
		return this.foldProperty().get();
	}

	public final void setFold(final boolean fold) {
		this.foldProperty().set(fold);
	}

}
