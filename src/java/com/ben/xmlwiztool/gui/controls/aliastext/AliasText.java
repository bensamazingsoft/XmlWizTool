package com.ben.xmlwiztool.gui.controls.aliastext;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AliasText extends Text {

	ElementWrapper wrapper;

	public AliasText(ElementWrapper rootWrapper, ElementWrapper current, String text) {

		super(text);
		setFill(Color.RED);

		this.wrapper = current;
	}

	public ElementWrapper getWrapper() {
		return wrapper;
	}

	public void setWrapper(ElementWrapper wrapper) {
		this.wrapper = wrapper;
	}

}
