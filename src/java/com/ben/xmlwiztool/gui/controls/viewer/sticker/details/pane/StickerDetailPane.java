
package com.ben.xmlwiztool.gui.controls.viewer.sticker.details.pane;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.wrapper.AttributeWrapper;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.gui.controls.pathbox.PathBox;
import com.ben.xmlwiztool.gui.controls.viewer.sticker.Sticker;
import com.ben.xmlwiztool.gui.controls.viewer.sticker.details.pane.cellfactory.AttrTableCell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class StickerDetailPane extends VBox implements Initializable {

	private Sticker sticker;
	private ResourceBundle bundle;
	private PathBox pathBox;

	@FXML
	Text rootName;

	@FXML
	Text tagName;

	@FXML
	TableView<AttributeWrapper> attrTable;

	@FXML
	TableColumn<AttributeWrapper, String> nameCol;

	@FXML
	TableColumn<AttributeWrapper, String> valCol;

	@FXML
	TextArea valueTextArea;

	public StickerDetailPane(Sticker sticker) {

		setSpacing(10.0);

		getStylesheets().add("/css/styles.css");
		getStyleClass().add("StickerDetailPane");

		this.sticker = sticker;

		bundle = AppContext.getInstance().getBundle();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/StickerDetailPane.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// root tag name
		LinkedList<ElementWrapper> ancestors = sticker.getWrapper().getAncestors();
		String rootTagName = "";

		rootTagName = ancestors.peekFirst().getElement().getTagName();

		rootName.textProperty().set(rootTagName);

		// tag name
		tagName.textProperty().set(sticker.getWrapper().getElement().getTagName());

		// value
		valueTextArea.setPrefRowCount(2);
		valueTextArea.setText(sticker.getWrapper().getValue());

		// attributes
		makeAttrTable();

		// path box
		pathBox = new PathBox(sticker);
		getChildren().add(pathBox);
	}

	private void makeAttrTable() {

		nameCol.setCellValueFactory(new PropertyValueFactory<AttributeWrapper, String>("name"));
		valCol.setCellValueFactory(new PropertyValueFactory<AttributeWrapper, String>("value"));
		valCol.setCellFactory(
				new Callback<TableColumn<AttributeWrapper, String>, TableCell<AttributeWrapper, String>>() {

					@Override
					public TableCell<AttributeWrapper, String> call(TableColumn<AttributeWrapper, String> param) {
						return new AttrTableCell();
					}
				});

		ObservableList<AttributeWrapper> tableList = FXCollections.observableArrayList();
		NamedNodeMap nodeMap = sticker.getWrapper().getElement().getAttributes();
		for (int i = 0; i < nodeMap.getLength(); i++) {

			Attr attr = (Attr) nodeMap.item(i);
			tableList.add(new AttributeWrapper(attr.getName(), attr.getValue()));

		}

		attrTable.setPrefHeight(80.0 + (Math.max((tableList.size() - 1), 0) * 80.0));
		attrTable.setItems(tableList);
	}

}
