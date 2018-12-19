
package com.ben.xmlwiztool.application.wrapper;

import java.util.LinkedList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
		setVisible(true);

	}

	public ElementWrapper(Element element) {

		this();
		this.element = element;

	}

	@Override
	public String toString() {

		return "ElementWrapper [element=" + element.getTagName() + "]";
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

		if (element != null && element.hasChildNodes()) {

			Node clone = element.cloneNode(true);

			for (int i = 0; i < clone.getChildNodes().getLength(); i++) {

				Node node = clone.getChildNodes().item(i);
				if (node.getNodeType() != Node.TEXT_NODE) {

					clone.removeChild(node);
				}
			}

			return element.getTextContent();
		}

		return "";
	}

	public LinkedList<ElementWrapper> getAncestors() {

		LinkedList<ElementWrapper> ancestors = new LinkedList<>();

		ElementWrapper ancestor = parent;
		while (ancestor != null) {
			ancestors.addFirst(ancestor);
			ancestor = ancestor.getParent();
		}

		return ancestors;
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
