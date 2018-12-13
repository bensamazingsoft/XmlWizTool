package com.ben.xmlwiztool.gui.viewer;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.ComplexElementWrapper;
import com.ben.xmlwiztool.gui.facade.GuiFacade;
import com.ben.xmlwiztool.gui.viewer.sticker.Sticker;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ElementViewer extends HBox {

	private final ElementWrapper wrapper;
	private SimpleDoubleProperty TABSIZE = GuiFacade.getInstance().getTabSize();
	private SimpleBooleanProperty visible = new SimpleBooleanProperty();
	private Region region = new Region();
	private VBox content = new VBox();

	public ElementViewer(ElementWrapper wrapper) {
		super();
		this.wrapper = wrapper;

		bindProperties();

		showOrNot();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void bindProperties() {
		region.minWidthProperty().bind(TABSIZE);

		visible.bindBidirectional(wrapper.visibleProperty());
		visible.addListener((ChangeListener) (o, oldVal, newVal) -> {
			showOrNot();
		});
	}

	private void showOrNot() {
		if (isVisible()) {
			show();
		} else {
			hide();
		}
	}

	private void show() {

		this.getChildren().add(region);
		this.getChildren().add(content);

		content.getChildren().add(new Sticker(wrapper));

		if (wrapper instanceof ComplexElementWrapper) {

			ComplexElementWrapper complexElement = (ComplexElementWrapper) wrapper;

			if (!complexElement.getChildren().isEmpty()) {

				for (ElementWrapper child : complexElement.getChildren()) {
					content.getChildren().add(new ElementViewer(child));
				}

			}

		}
	}

	private void hide() {
		this.getChildren().clear();
	}

	public ElementWrapper getElement() {
		return wrapper;
	}

	public final SimpleDoubleProperty TABSIZEProperty() {
		return this.TABSIZE;
	}

	public final double getTABSIZE() {
		return this.TABSIZEProperty().get();
	}

	public final void setTABSIZE(final double TABSIZE) {
		this.TABSIZEProperty().set(TABSIZE);
	}

}
