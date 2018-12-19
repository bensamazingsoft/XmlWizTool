
package com.ben.xmlwiztool.gui.viewer.sticker;

import java.util.LinkedList;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.ComplexElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.SimpleElementWrapper;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Sticker extends VBox {

	private HBox top = new HBox();
	private VBox bottom = new VBox();
	private Node nameLabel;

	private static final String IMAGEPATH = "images/buttons/";

	private final Image toggleFoldButSelectedImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "toggleFoldButUnSelected.png"));
	private final Image toggleFoldButUnSelectedImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "toggleFoldButSelected.png"));
	private final ImageView toggleFoldButImgView = new ImageView();

	private final Image toggleShowButSelectedImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "toggleShowButSelected.png"));
	private final Image toggleShowButUnSelectedImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "toggleFoldButSelected.png"));
	private final ImageView toggleShowButImgView = new ImageView();

	private final Image pathLabelImg = new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "pathLabelImg.png"));
	private final ImageView pathLabelImgView = new ImageView(pathLabelImg);

	public Sticker(ElementWrapper wrapper) {

		this.getStyleClass().add("sticker");
		this.getChildren().addAll(top, bottom);
		this.minWidthProperty().bind(GuiFacade.getInstance().tabLengthProperty());
		// this.maxWidthProperty().bind(GuiFacade.getInstance().tabLengthProperty());

		// top
		if (wrapper instanceof ComplexElementWrapper) {

			addFoldBut(wrapper);
		}

		nameLabel = new Label(wrapper.getElement().getTagName());
		top.getChildren().add(nameLabel);

		addShowBut(wrapper);

		// bottom
		bottom.setId("bottom");
		if (wrapper.getValue().length() > 0 && wrapper instanceof SimpleElementWrapper) {
			bottom.getChildren()
					.add(new Label(AppContext.getInstance().getProperties().get("value") + " : " + wrapper.getValue()));
		}

		HBox pathBox = new HBox(pathLabelImgView, getElemPathTextFlow(wrapper));
		bottom.getChildren().add(pathBox);

	}

	private void addShowBut(ElementWrapper wrapper) {
		ToggleButton toggleShowBut = new ToggleButton();
		toggleShowBut.setOnAction((Event) -> {
			toggleShow(wrapper);
		});

		toggleShowBut.setGraphic(toggleShowButImgView);
		toggleShowButImgView.imageProperty().bind(Bindings.when(wrapper.visibleProperty())
				.then(toggleShowButSelectedImg).otherwise(toggleShowButUnSelectedImg));

		Region region = new Region();
		top.getChildren().add(region);
		HBox.setHgrow(region, Priority.ALWAYS);
		top.getChildren().add(toggleShowBut);
	}

	private void addFoldBut(ElementWrapper wrapper) {
		ToggleButton toggleFoldBut = new ToggleButton();
		toggleFoldBut.setOnAction((Event) -> {
			toggleFold(wrapper);
		});

		toggleFoldBut.setGraphic(toggleFoldButImgView);
		toggleFoldButImgView.imageProperty().bind(Bindings.when(wrapper.foldProperty()).then(toggleFoldButSelectedImg)
				.otherwise(toggleFoldButUnSelectedImg));

		top.getChildren().add(toggleFoldBut);
	}

	private TextFlow getElemPathTextFlow(ElementWrapper wrapper) {

		TextFlow textFlow = new TextFlow();
		LinkedList<ElementWrapper> ancestors = new LinkedList<>(wrapper.getAncestors());
		ElementWrapper rootWrapper = ancestors.peekFirst();

		while (!ancestors.isEmpty()) {

			ElementWrapper current = ancestors.poll();

			Text text = testForTagNameAlias(rootWrapper, current);

			textFlow.getChildren().add(text);

			Text sep = new Text(GuiFacade.getInstance().getSeparator());
			textFlow.getChildren().add(sep);
		}

		Text thisTag = testForTagNameAlias(rootWrapper, wrapper);
		textFlow.getChildren().add(thisTag);

		return textFlow;
	}

	private Text testForTagNameAlias(ElementWrapper rootWrapper, ElementWrapper current) {
		// String tag =
		// AppContext.getInstance().getTagNameAliasManager().getAlias(rootWrapper,
		// current);

		Text text = new Text(current.getElement().getTagName());

		if (AppContext.getInstance().getTagNameAliasManager().aliased(current)) {

			text = new Text(AppContext.getInstance().getTagNameAliasManager().getAlias(rootWrapper, current));
			text.setFill(Color.RED);
		}

		return text;
	}

	private void toggleShow(ElementWrapper wrapper) {

		wrapper.setVisible(!wrapper.isVisible());

	}

	private void toggleFold(ElementWrapper wrapper) {

		wrapper.setFold(!wrapper.isFold());
	}

}
