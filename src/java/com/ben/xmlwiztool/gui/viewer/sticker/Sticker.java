package com.ben.xmlwiztool.gui.viewer.sticker;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.ComplexElementWrapper;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Sticker extends VBox {

	private static final String IMAGEPATH = "images/gui/buttons";
	private HBox top = new HBox();
	private HBox bottom = new HBox();
	private Node nameLabel;

	public Sticker(ElementWrapper wrapper) {

		this.getChildren().addAll(top, bottom);

		if (wrapper instanceof ComplexElementWrapper) {

			ToggleButton toggleFoldBut = new ToggleButton();
			toggleFoldBut.setOnAction((Event) -> {
				toggleFold(wrapper);
			});

			Image toggleFoldButSelectedImg = new Image(
					getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "toggleSeparatorButSelected.png"));
			Image toggleFoldButUnSelectedImg = new Image(
					getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "toggleSeparatorButUnSelected.png"));
			ImageView toggleFoldButImgView = new ImageView();

			toggleFoldBut.setGraphic(toggleFoldButImgView);
			toggleFoldButImgView.imageProperty().bind(Bindings.when(toggleFoldBut.selectedProperty())
					.then(toggleFoldButSelectedImg).otherwise(toggleFoldButUnSelectedImg));

			top.getChildren().add(toggleFoldBut);
		}

		nameLabel = new Label(wrapper.getElement().getTagName());
		top.getChildren().add(nameLabel);

		ToggleButton toggleShowBut = new ToggleButton();
		toggleShowBut.setOnAction((Event) -> {
			toggleShow(wrapper);
		});

		Image toggleShowButSelectedImg = new Image(
				getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "toggleShowButSelected.png"));
		Image toggleShowButUnSelectedImg = new Image(
				getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "toggleShowButUnSelected.png"));
		ImageView toggleFoldButImgView = new ImageView();

		toggleShowBut.setGraphic(toggleFoldButImgView);
		toggleFoldButImgView.imageProperty().bind(Bindings.when(toggleShowBut.selectedProperty())
				.then(toggleShowButSelectedImg).otherwise(toggleShowButUnSelectedImg));

		top.getChildren().add(toggleShowBut);

	}

	private void toggleShow(ElementWrapper wrapper) {
		wrapper.setVisible(!wrapper.isVisible());

	}

	private void toggleFold(ElementWrapper wrapper) {
		wrapper.setFold(!wrapper.isFold());
	}

}
