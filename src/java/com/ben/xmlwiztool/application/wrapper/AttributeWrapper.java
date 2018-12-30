
package com.ben.xmlwiztool.application.wrapper;

import javafx.beans.property.SimpleStringProperty;

public class AttributeWrapper
{

      private final SimpleStringProperty name  = new SimpleStringProperty();;
      private final SimpleStringProperty value = new SimpleStringProperty();;


      public AttributeWrapper()
      {

	    this("", "");

      }


      public AttributeWrapper(String name, String value)
      {

	    setName(name);
	    setValue(value);

      }


      public final SimpleStringProperty nameProperty()
      {

	    return this.name;
      }


      public final String getName()
      {

	    return this.nameProperty().get();
      }


      public final void setName(final String name)
      {

	    this.nameProperty().set(name);
      }


      public final SimpleStringProperty valueProperty()
      {

	    return this.value;
      }


      public final String getValue()
      {

	    return this.valueProperty().get();
      }


      public final void setValue(final String value)
      {

	    this.valueProperty().set(value);
      }


      @Override
      public String toString()
      {

	    return "AttributeWrapper [name=" + name + ", value=" + value + "]";
      }

}
