
package com.ben.xmlwiztool.gui.viewer.sticker;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.ComplexElementWrapper;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Sticker extends VBox
{

      private static final String IMAGEPATH = "images/buttons/";
      private HBox		  top	    = new HBox();
      private HBox		  bottom    = new HBox();
      private Node		  nameLabel;


      public Sticker(ElementWrapper wrapper)
      {

	    this.getStyleClass().add("sticker");

	    this.getChildren().addAll(top, bottom);

	    this.minWidthProperty().bind(GuiFacade.getInstance().tabLengthProperty());
	    this.maxWidthProperty().bind(GuiFacade.getInstance().tabLengthProperty());

	    if (wrapper instanceof ComplexElementWrapper)
	    {

		  ToggleButton toggleFoldBut = new ToggleButton();
		  toggleFoldBut.setOnAction((Event) -> {
			toggleFold(wrapper);
		  });

		  Image toggleFoldButSelectedImg = new Image(
			      getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "toggleFoldButUnSelected.png"));
		  Image toggleFoldButUnSelectedImg = new Image(
			      getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "toggleFoldButSelected.png"));
		  ImageView toggleFoldButImgView = new ImageView();

		  toggleFoldBut.setGraphic(toggleFoldButImgView);
		  toggleFoldButImgView.imageProperty().bind(Bindings.when(wrapper.foldProperty())
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
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH +
				    "toggleShowButSelected.png"));
	    Image toggleShowButUnSelectedImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH +
				    "toggleFoldButSelected.png"));
	    ImageView toggleFoldButImgView = new ImageView();

	    toggleShowBut.setGraphic(toggleFoldButImgView);
	    toggleFoldButImgView.imageProperty().bind(Bindings.when(wrapper.visibleProperty())
			.then(toggleShowButSelectedImg).otherwise(toggleShowButUnSelectedImg));

	    top.getChildren().add(toggleShowBut);

	    // TODO fix this, it returns all descendant as well as text content
	    // if (wrapper.getElement().getTextContent().length() > 0) {
	    // bottom.getChildren().add(new
	    // Label(wrapper.getElement().getTextContent()));
	    // }

      }


      private void toggleShow(ElementWrapper wrapper)
      {

	    wrapper.setVisible(!wrapper.isVisible());

      }


      private void toggleFold(ElementWrapper wrapper)
      {

	    wrapper.setFold(!wrapper.isFold());
      }

}
