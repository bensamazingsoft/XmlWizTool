
package com.ben.xmlwiztool.application.wrapper;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.ben.xmlwiztool.observable.Observer;

import javafx.beans.property.SimpleBooleanProperty;

public abstract class ElementWrapper
{

      protected Element		      element;
      protected Set<AttributeWrapper> attributes = new HashSet<>();
      protected SimpleBooleanProperty visible, expand, empty;
      protected ElementWrapper	      parent;
      private Set<Observer>	      observers	 = new HashSet<>();
      private boolean		      changed	 = false;


      public ElementWrapper()
      {

	    super();

	    visible = new SimpleBooleanProperty();
	    expand = new SimpleBooleanProperty();
	    empty = new SimpleBooleanProperty();

	    setExpand(true);
	    setVisible(true);
	    setEmpty(true);

      }


      public ElementWrapper(Element element)
      {

	    this();
	    this.element = element;

	    NamedNodeMap nodeMap = element.getAttributes();
	    for (int i = 0; i < nodeMap.getLength(); i++)
	    {

		  Attr attr = (Attr) nodeMap.item(i);
		  attributes.add(new AttributeWrapper(attr.getName(), attr.getValue()));

	    }

      }


      @Override
      public String toString()
      {

	    return "ElementWrapper [element=" + element.getTagName() + "]";
      }


      @Override
      public int hashCode()
      {

	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((element == null) ? 0 : element.hashCode());
	    return result;
      }


      @Override
      public boolean equals(Object obj)
      {

	    if (this == obj)
		  return true;
	    if (obj == null)
		  return false;
	    if (getClass() != obj.getClass())
		  return false;
	    ElementWrapper other = (ElementWrapper) obj;
	    if (element == null)
	    {
		  if (other.element != null)
			return false;
	    }
	    else if (!element.getTextContent().equals(other.element.getTextContent()))
		  return false;
	    return true;
      }


      public String getValue()
      {

	    if (element != null && element.hasChildNodes())
	    {

		  Node clone = element.cloneNode(true);

		  for (int i = 0; i < clone.getChildNodes().getLength(); i++)
		  {

			Node node = clone.getChildNodes().item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{

			      clone.removeChild(node);
			}
		  }

		  return clone.getTextContent();
	    }

	    return "";
      }


      public LinkedList<ElementWrapper> getLineage()
      {

	    LinkedList<ElementWrapper> lineage = new LinkedList<>();

	    ElementWrapper relative = this;
	    while (relative != null)
	    {
		  lineage.addFirst(relative);
		  relative = relative.getParent();
	    }

	    return lineage;
      }


      public LinkedList<String> getTextLineage()
      {

	    return getLineage().stream().map(ElementWrapper::getElement).map(Element::getTagName)
			.collect(Collectors.toCollection(LinkedList::new));

      }


      public Element getElement()
      {

	    return element;
      }


      public void setElement(Element element)
      {

	    this.element = element;
      }


      public ElementWrapper getParent()
      {

	    return parent;
      }


      public void setParent(ElementWrapper parent)
      {

	    this.parent = parent;
      }


      public final SimpleBooleanProperty visibleProperty()
      {

	    return this.visible;
      }


      public final boolean isVisible()
      {

	    return this.visibleProperty().get();
      }


      public final void setVisible(final boolean visible)
      {

	    this.visibleProperty().set(visible);
      }


      public void setBranchVisible(boolean b)
      {

	    setVisible(b);

	    ElementWrapper father = parent;

	    while (father != null)
	    {
		  father.setVisible(b);
		  father = father.getParent();
	    }

      }


      public void setBranchEmpty(boolean b)
      {

	    setEmpty(b);

	    ElementWrapper father = parent;

	    while (father != null)
	    {
		  father.setEmpty(b);
		  father = father.getParent();
	    }

      }


      public boolean match(String text)
      {

	    String tagName = element.getTagName().toLowerCase().trim();
	    String val = getValue().toLowerCase().trim();

	    return match(text, tagName) || match(text, val);
      }


      private boolean match(String patt, String text)
      {

	    int length = text.length() - patt.length();

	    for (int i = 0; i <= length; i++)
	    {

		  if (text.regionMatches(i, patt, 0, patt.length()))
		  {
			return true;
		  }

	    }

	    return false;
      }


      public final SimpleBooleanProperty emptyProperty()
      {

	    return this.empty;
      }


      public final boolean isEmpty()
      {

	    return this.emptyProperty().get();
      }


      public final void setEmpty(final boolean filterable)
      {

	    this.emptyProperty().set(filterable);
      }


      public final SimpleBooleanProperty expandProperty()
      {

	    return this.expand;
      }


      public final boolean isExpand()
      {

	    return this.expandProperty().get();
      }


      public final void setExpand(final boolean expand)
      {

	    this.expandProperty().set(expand);
      }


      public Set<AttributeWrapper> getAttributes()
      {

	    return attributes;
      }


      public void setAttributes(Set<AttributeWrapper> attributes)
      {

	    this.attributes = attributes;
      }


      abstract public Set<ElementWrapper> getLeaves();

}
