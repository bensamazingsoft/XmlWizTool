
package com.ben.xmlwiztool.gui.controls.viewer.sticker;

import java.util.LinkedList;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.ComplexElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.SimpleElementWrapper;
import com.ben.xmlwiztool.gui.controls.aliastext.AliasText;
import com.ben.xmlwiztool.gui.controls.menu.context.sticker.StickerContextMenu;
import com.ben.xmlwiztool.gui.facade.GuiFacade;
import com.ben.xmlwiztool.gui.tooltips.Tips;
import com.ben.xmlwiztool.gui.tooltips.factory.TipsFactory;

import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Sticker extends VBox {

	private HBox top = new HBox();
	private HBox bottom = new HBox();
	private Label nameLabel;
	private ContextMenu contextMenu;
	private Sticker sticker;
	private ElementWrapper wrapper;
	private double MaxComputedTextControlSize;
	private Label valueLabel;
	private HBox pathBox;

	public Sticker(ElementWrapper wrapper) {

		this.wrapper = wrapper;
		this.sticker = this;

		this.getStyleClass().add("sticker");
		this.getChildren().addAll(top, bottom);

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

		AnchorPane anchor = new AnchorPane();
		if (!wrapper.getAttributes().isEmpty()) {
			Label attrLabel = new Label("Attr");
			attrLabel.setId("attrLabel");

			AnchorPane.setRightAnchor(attrLabel, 0.0);
			AnchorPane.setTopAnchor(attrLabel, 0.0);
			anchor.getChildren().add(attrLabel);
			attrLabel.setTooltip(TipsFactory.getTips(Tips.ATTR, wrapper));
		}

		VBox valPathBox = new VBox();
		if (wrapper.getValue().length() > 0 && wrapper instanceof SimpleElementWrapper) {

			valueLabel = new Label(
					AppContext.getInstance().getBundle().getString("value") + " : " + wrapper.getValue());
			valueLabel.getStyleClass().add("valueLabel");

			valPathBox.getChildren().add(valueLabel);
		}

		pathBox = new HBox(new Label("->"), makeElemPathTextFlow(wrapper));
		valPathBox.getChildren().add(pathBox);

		bottom.setId("bottom");
		bottom.setSpacing(10);
		bottom.getChildren().addAll(valPathBox, anchor);

	}

	public double computeMaxSize() {

		double nameLabelWidth = getLayoutWidth(nameLabel.getText());
		double valueLabelWidth = valueLabel != null ? getLayoutWidth(valueLabel.getText()) : 0;
		double pathboxWidth = pathBox != null ? pathBox.getBoundsInLocal().getWidth() : 0;

		double max = Math.max(nameLabelWidth, Math.max(valueLabelWidth, pathboxWidth));

		return 50 + max;

	}

	private double getLayoutWidth(String str) {

		final Text txt = new Text(str);
		Scene scene = new Scene(new Group(txt));
		scene.getStylesheets().add("/css/styles.css");
		txt.getStyleClass().add("tagNameLabel");
		txt.applyCss();
		double width = txt.getLayoutBounds().getWidth();

		return width;
	}

	private void addShowBut(ElementWrapper wrapper) {

		Button hideBut = new Button();
		hideBut.setText("X");
		hideBut.setStyle("-fx-font-size:0.7em");
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
		LinkedList<ElementWrapper> lineage = new LinkedList<>(wrapper.getLineage());
		ElementWrapper rootWrapper = lineage.peekFirst();

		while (!lineage.isEmpty()) {

			ElementWrapper current = lineage.poll();

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

	public double getMaxComputedTextControlSize() {

		return MaxComputedTextControlSize;
	}

	public void setMaxComputedTextControlSize(double maxComputedTextControlSize) {

		MaxComputedTextControlSize = maxComputedTextControlSize;
	}

}
