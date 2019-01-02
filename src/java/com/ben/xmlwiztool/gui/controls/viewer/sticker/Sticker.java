
package com.ben.xmlwiztool.gui.controls.viewer.sticker;

import java.util.LinkedList;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.ComplexElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.SimpleElementWrapper;
import com.ben.xmlwiztool.gui.controls.aliastext.AliasText;
import com.ben.xmlwiztool.gui.controls.menu.context.sticker.StickerContextMenu;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Sticker extends VBox {

	private HBox top = new HBox();
	private VBox bottom = new VBox();
	private Node nameLabel;
	private ContextMenu contextMenu;
	private Sticker sticker;
	private ElementWrapper wrapper;

	public Sticker(ElementWrapper wrapper) {

		this.wrapper = wrapper;
		this.sticker = this;

		this.getStyleClass().add("sticker");
		this.getChildren().addAll(top, bottom);
		this.minWidthProperty().bind(GuiFacade.getInstance().tabLengthProperty());

		this.contextMenu = new StickerContextMenu(this);
		this.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

			@Override
			public void handle(ContextMenuEvent event) {

				contextMenu.show(sticker, event.getScreenX(), event.getScreenY());
			}
		});

		// top
		if (wrapper instanceof ComplexElementWrapper && !GuiFacade.getInstance().isTreeView()) {

			addExpandBut(wrapper);
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

		HBox pathBox = new HBox(new Label("->"), makeElemPathTextFlow(wrapper));
		bottom.getChildren().add(pathBox);

		this.setMaxWidth(USE_COMPUTED_SIZE);

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

	private void addExpandBut(ElementWrapper wrapper) {

		ToggleButton toggleExpandBut = new ToggleButton();
		toggleExpandBut.getStyleClass().add("expandBut");
		toggleExpandBut.textProperty().bind(Bindings.when(wrapper.expandProperty()).then("-").otherwise("+"));
		toggleExpandBut.setOnAction((Event) -> {
			toggleExpand(wrapper);
		});

		top.getChildren().add(toggleExpandBut);
	}

	public static TextFlow makeElemPathTextFlow(ElementWrapper wrapper) {

		TextFlow textFlow = new TextFlow();
		LinkedList<ElementWrapper> ancestors = new LinkedList<>(wrapper.getAncestors());
		ElementWrapper rootWrapper = ancestors.peekFirst();

		while (!ancestors.isEmpty()) {

			ElementWrapper current = ancestors.poll();

			Text text = makeAliasText(rootWrapper, current);

			textFlow.getChildren().add(text);

			Text sep = new Text(GuiFacade.getInstance().getSeparator());

			sep.textProperty().bind(GuiFacade.getInstance().separatorProperty());

			textFlow.getChildren().add(sep);
		}

		return textFlow;
	}

	public static Text makeAliasText(ElementWrapper rootWrapper, ElementWrapper current) {

		Text text = new Text(current.getElement().getTagName());

		if (AppContext.getInstance().getTagNameAliasManager().aliased(current)) {

			text = new AliasText(rootWrapper, current, text.getText());
		}

		return text;
	}

	private void hide(ElementWrapper wrapper) {

		wrapper.setVisible(!wrapper.isVisible());

	}

	private void toggleExpand(ElementWrapper wrapper) {

		wrapper.setExpand(!wrapper.isExpand());
	}

	public ElementWrapper getWrapper() {

		return wrapper;
	}

	public void setWrapper(ElementWrapper wrapper) {

		this.wrapper = wrapper;
	}

}
