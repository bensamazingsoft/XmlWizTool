package com.ben.xmlwiztool.gui.viewer;

import java.io.IOException;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.ComplexElementWrapper;
import com.ben.xmlwiztool.gui.viewer.sticker.Sticker;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class Viewer extends HBox {

	ElementWrapper wrapper;
	private SimpleDoubleProperty TABSIZE;
	private Region region;
	private VBox content;

	public Viewer(ElementWrapper wrapper) throws NumberFormatException, IOException {
		super();
		this.wrapper = wrapper;

		// TODO get tabSize in the facade and bind it to the region width

		region = new Region();

		this.getChildren().add(region);

		content = new VBox();
		content.getChildren().add(new Sticker(wrapper));

		if (wrapper instanceof ComplexElementWrapper) {

			ComplexElementWrapper complexElement = (ComplexElementWrapper) wrapper;

			if (!complexElement.getChildren().isEmpty()) {

				for (ElementWrapper child : complexElement.getChildren()) {
					content.getChildren().add(new Viewer(child));
				}

			}

		}

	}

	public ElementWrapper getElement() {
		return wrapper;
	}

	public void setElement(ElementWrapper element) {
		this.wrapper = element;
	}

}
