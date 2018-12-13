package com.ben.xmlwiztool.gui.viewer.sticker;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.ComplexElementWrapper;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Sticker extends VBox {

	private HBox top = new HBox();
	private HBox bottom = new HBox();
	private Node nameLabel;

	public Sticker(ElementWrapper wrapper) {
		// TODO Sticker(ElementWrapper wrapper)

		this.getChildren().addAll(top, bottom);

		if (wrapper instanceof ComplexElementWrapper) {

			Button toggleFoldBut = new Button();
			toggleFoldBut.setOnAction((Event) -> {
				toggleFold(wrapper);
			});

			top.getChildren().add(toggleFoldBut);
		}

		nameLabel = new Label(wrapper.getElement().getTagName());
		top.getChildren().add(nameLabel);

		Button toggleShowBut = new Button();
		toggleShowBut.setOnAction((Event) -> {
			toggleShow(wrapper);
		});

		top.getChildren().add(toggleShowBut);

	}

	private void toggleShow(ElementWrapper wrapper) {
		wrapper.setVisible(!wrapper.isVisible());

	}

	private void toggleFold(ElementWrapper wrapper) {

		wrapper.setFold(!wrapper.isFold());

	}

}
