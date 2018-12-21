
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
	private SimpleBooleanProperty visibleElement = new SimpleBooleanProperty();
	private SimpleBooleanProperty fold = new SimpleBooleanProperty();
	private SimpleBooleanProperty filter = new SimpleBooleanProperty();
	private SimpleBooleanProperty state = new SimpleBooleanProperty();

	private Region region = new Region();
	private VBox content = new VBox();

	public ElementViewer(ElementWrapper wrapper) {

		super();

		this.getStyleClass().add("elementviewer");

		this.wrapper = wrapper;

		bindProperties();

		showOrNot();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void bindProperties() {

		region.minWidthProperty().bind(TABSIZE);

		// Node visibility binded to wrapper and viewer visible prop.
		visibleElement.bindBidirectional(visibleProperty());
		visibleElement.bindBidirectional(wrapper.visibleProperty());
		visibleElement.addListener((ChangeListener) (o, oldVal, newVal) -> {
			showOrNot();
		});

		fold.bindBidirectional(wrapper.foldProperty());
		fold.addListener((ChangeListener) (o, oldVal, newVal) -> {
			showOrNot();
		});

		filter.bind(wrapper.filterableProperty());

		state.bind(GuiFacade.getInstance().hideEmptyProperty());
		state.addListener((ChangeListener) (observable, oldVal, newVal) -> {
			showOrNot();
		});

	}

	private void showOrNot() {

		if (isVisible() && !isFiltered()) {
			show();
		} else {
			hide();
		}
	}

	private boolean isFiltered() {

		if (!GuiFacade.getInstance().isHideEmpty()) {
			return false;
		}

		if (!wrapper.isFilterable()) {
			return false;
		}

		return true;
	}

	private void show() {

		this.getChildren().clear();
		content.getChildren().clear();

		this.getChildren().add(region);
		this.getChildren().add(content);

		content.getChildren().add(new Sticker(wrapper));

		if (wrapper instanceof ComplexElementWrapper && !isFold()) {

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

	public final SimpleBooleanProperty foldProperty() {

		return this.fold;
	}

	public final boolean isFold() {

		return this.foldProperty().get();
	}

	public final void setFold(final boolean fold) {

		this.foldProperty().set(fold);
	}

	public final SimpleBooleanProperty visibleElementProperty() {

		return this.visibleElement;
	}

	public final boolean isVisibleElement() {

		return this.visibleElementProperty().get();
	}

	public final void setVisibleElement(final boolean visibleElement) {

		this.visibleElementProperty().set(visibleElement);
	}

	public VBox getContent() {
		return content;
	}

	public void setContent(VBox content) {
		this.content = content;
	}

}
