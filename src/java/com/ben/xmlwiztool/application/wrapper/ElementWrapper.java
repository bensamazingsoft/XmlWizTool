
package com.ben.xmlwiztool.application.wrapper;

import java.util.LinkedList;
import java.util.stream.Collectors;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javafx.beans.property.SimpleBooleanProperty;

public abstract class ElementWrapper {

	protected Element element;
	protected SimpleBooleanProperty visible, expand, filterable;
	protected ElementWrapper parent;

	public ElementWrapper() {

		super();

		visible = new SimpleBooleanProperty();
		// fold = new SimpleBooleanProperty();
		expand = new SimpleBooleanProperty();
		filterable = new SimpleBooleanProperty();

		// setFold(false);
		setExpand(true);
		setVisible(true);
		setFilterable(true);

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
				if (node.getNodeType() == Node.ELEMENT_NODE) {

					clone.removeChild(node);
				}
			}

			return clone.getTextContent();
		}

		return "";
	}

	public LinkedList<ElementWrapper> getLineage() {

		LinkedList<ElementWrapper> lineage = new LinkedList<>();

		ElementWrapper relative = this;
		while (relative != null) {
			lineage.addFirst(relative);
			relative = relative.getParent();
		}

		return lineage;
	}

	public LinkedList<String> getTextLineage() {

		return getLineage().stream().map(ElementWrapper::getElement).map(Element::getTagName)
				.collect(Collectors.toCollection(LinkedList::new));

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

	public void setBranchVisible(boolean b) {

		setVisible(b);

		ElementWrapper father = parent;

		while (father != null) {
			father.setVisible(b);
			father = father.getParent();
		}

	}

	public void setBranchFilterable(boolean b) {

		setFilterable(b);

		ElementWrapper father = parent;

		while (father != null) {
			father.setFilterable(b);
			father = father.getParent();
		}

	}

	public boolean match(String text) {

		String tagName = element.getTagName().toLowerCase().trim();
		String val = getValue().toLowerCase().trim();

		return match(text, tagName) || match(text, val);
	}

	private boolean match(String patt, String text) {

		int length = text.length() - patt.length();

		for (int i = 0; i < length; i++) {

			if (text.regionMatches(i, patt, 0, patt.length())) {
				return true;
			}

		}

		return false;
	}

	public final SimpleBooleanProperty filterableProperty() {
		return this.filterable;
	}

	public final boolean isFilterable() {
		return this.filterableProperty().get();
	}

	public final void setFilterable(final boolean filterable) {
		this.filterableProperty().set(filterable);
	}

	public final SimpleBooleanProperty expandProperty() {
		return this.expand;
	}

	public final boolean isExpand() {
		return this.expandProperty().get();
	}

	public final void setExpand(final boolean expand) {
		this.expandProperty().set(expand);
	}

}
