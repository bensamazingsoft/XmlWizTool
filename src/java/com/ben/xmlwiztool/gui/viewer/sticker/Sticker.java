
package com.ben.xmlwiztool.gui.viewer.sticker;

import java.util.LinkedList;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.ComplexElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.SimpleElementWrapper;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
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

	public Sticker(ElementWrapper wrapper) {

		this.getStyleClass().add("sticker");
		this.getChildren().addAll(top, bottom);
		this.minWidthProperty().bind(GuiFacade.getInstance().tabLengthProperty());
		// this.maxWidthProperty().bind(GuiFacade.getInstance().tabLengthProperty());

		// top
		if (wrapper instanceof ComplexElementWrapper) {

			addFoldBut(wrapper);
		}

		nameLabel = new Label("<" + wrapper.getElement().getTagName() + ">");
		nameLabel.getStyleClass().add("tagNameLabel");
		top.getChildren().add(nameLabel);

		addShowBut(wrapper);

		// bottom
		bottom.setId("bottom");
		if (wrapper.getValue().length() > 0 && wrapper instanceof SimpleElementWrapper) {

			Label label = new Label(
					AppContext.getInstance().getBundle().getString("value") + " : " + wrapper.getValue());
			label.getStyleClass().add("valueLabel");

			bottom.getChildren().add(label);
		}

		HBox pathBox = new HBox(new Label("->"), getElemPathTextFlow(wrapper));
		bottom.getChildren().add(pathBox);

	}

	private void addShowBut(ElementWrapper wrapper) {

		Button hideBut = new Button();
		// hideBut.getStyleClass().add("foldBut");
		hideBut.setText("X");
		hideBut.setOnAction((Event) -> {
			hide(wrapper);
		});

		Region region = new Region();
		top.getChildren().add(region);
		HBox.setHgrow(region, Priority.ALWAYS);
		top.getChildren().add(hideBut);
	}

	private void addFoldBut(ElementWrapper wrapper) {

		ToggleButton toggleFoldBut = new ToggleButton();
		toggleFoldBut.getStyleClass().add("foldBut");
		toggleFoldBut.textProperty().bind(Bindings.when(wrapper.foldProperty()).then("+").otherwise("-"));
		toggleFoldBut.setOnAction((Event) -> {
			toggleFold(wrapper);
		});

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

			sep.textProperty().bind(GuiFacade.getInstance().separatorProperty());

			textFlow.getChildren().add(sep);
		}

		Text thisTag = testForTagNameAlias(rootWrapper, wrapper);
		textFlow.getChildren().add(thisTag);

		return textFlow;
	}

	private Text testForTagNameAlias(ElementWrapper rootWrapper, ElementWrapper current) {

		Text text = new Text(current.getElement().getTagName());

		if (AppContext.getInstance().getTagNameAliasManager().aliased(current)) {

			text = new Text(AppContext.getInstance().getTagNameAliasManager().getAlias(rootWrapper, current));
			text.setFill(Color.RED);
		}

		return text;
	}

	private void hide(ElementWrapper wrapper) {

		wrapper.setVisible(!wrapper.isVisible());

	}

	private void toggleFold(ElementWrapper wrapper) {

		wrapper.setFold(!wrapper.isFold());
	}

}
