package com.ben.xmlwiztool.gui.controls.aliastext;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AliasText extends Text {

	ElementWrapper wrapper;

	public AliasText(ElementWrapper rootWrapper, ElementWrapper current, String text) {

		super(text);
		this.wrapper = current;

		// bind to the original alias text property in the alias manager
		SimpleStringProperty aliasStringProp = AppContext.getInstance().getTagNameAliasManager().getAlias(rootWrapper,
				current);
		textProperty().bindBidirectional(aliasStringProp);

		// TODO this color should be a setting
		setFill(Color.RED);

	}

	public ElementWrapper getWrapper() {
		return wrapper;
	}

	public void setWrapper(ElementWrapper wrapper) {
		this.wrapper = wrapper;
	}

}
