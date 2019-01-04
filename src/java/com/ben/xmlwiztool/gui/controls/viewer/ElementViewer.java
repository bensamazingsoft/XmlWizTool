
package com.ben.xmlwiztool.gui.controls.viewer;

import java.util.ArrayList;
import java.util.List;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.ComplexElementWrapper;
import com.ben.xmlwiztool.gui.controls.viewer.sticker.Sticker;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ElementViewer extends HBox {

	private final ElementWrapper wrapper;
	private final List<ElementViewer> foldContent = new ArrayList<>();
	private SimpleDoubleProperty TABSIZE = GuiFacade.getInstance().getTabSize();
	private SimpleBooleanProperty visibleElement = new SimpleBooleanProperty();
	private SimpleBooleanProperty expand = new SimpleBooleanProperty();
	private SimpleBooleanProperty filter = new SimpleBooleanProperty();
	private SimpleBooleanProperty state = new SimpleBooleanProperty();
	private Region region = new Region();
	private VBox content = new VBox();
	private Sticker sticker;

	public ElementViewer(ElementWrapper wrapper) {

		super();

		content.setSpacing(3.0);
		this.getStyleClass().add("elementviewer");

		this.wrapper = wrapper;

		bindProperties();

		init();

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

		expand.bindBidirectional(wrapper.expandProperty());
		expand.addListener((ChangeListener) (o, oldVal, newVal) -> {
			showOrNot();
		});

		filter.bind(wrapper.filterableProperty());

		state.bind(GuiFacade.getInstance().hideEmptyProperty());
		state.addListener((ChangeListener) (observable, oldVal, newVal) -> {
			showOrNot();
		});

	}

	private void init() {

		sticker = new Sticker(wrapper);

		if (wrapper instanceof ComplexElementWrapper && isExpand()) {

			ComplexElementWrapper complexElement = (ComplexElementWrapper) wrapper;

			if (!complexElement.getChildren().isEmpty()) {

				for (ElementWrapper child : complexElement.getChildren()) {

					if (GuiFacade.getInstance().isFastLoad() && child.isFilterable()) {
						continue;
					}
					foldContent.add(new ElementViewer(child));

				}

			}

		}

	}

	private void showOrNot() {

		hide();

		if (isVisible() && !isFiltered()) {
			show();
		}
	}

	private void show() {

		this.getChildren().add(region);
		this.getChildren().add(content);

		content.getChildren().add(sticker);

		if (wrapper instanceof ComplexElementWrapper && isExpand()) {

			content.getChildren().addAll(foldContent);

		}
	}

	private void hide() {

		this.getChildren().clear();
		this.content.getChildren().clear();
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

	public final SimpleBooleanProperty expandProperty() {

		return this.expand;
	}

	public final boolean isExpand() {

		return this.expandProperty().get();
	}

	public final void setExpand(final boolean fold) {

		this.expandProperty().set(fold);
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

	public List<ElementViewer> getFoldContent() {
		return foldContent;
	}

}
