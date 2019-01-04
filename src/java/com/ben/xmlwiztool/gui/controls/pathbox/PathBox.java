
package com.ben.xmlwiztool.gui.controls.pathbox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.tagname.aliaser.TagNameAliasManager;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.gui.controls.aliastext.AliasText;
import com.ben.xmlwiztool.gui.controls.viewer.sticker.Sticker;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PathBox extends VBox implements Initializable {

	private ResourceBundle bundle;
	private SimpleBooleanProperty modify;
	private TextFlow modFlow, flow;
	private Sticker sticker;
	private Map<TextField, String> initalState;

	@FXML
	StackPane stack;

	@FXML
	CheckBox globalCb;

	@FXML
	CheckBox saveCb;

	@FXML
	HBox controls;

	@FXML
	Button modifyBut;

	public PathBox(Sticker sticker) {

		this.sticker = sticker;
		initalState = new HashMap<>();

		spacingProperty().set(5.0);

		modify = new SimpleBooleanProperty();
		modify.addListener((o, oldVal, newVal) -> {
			flow.setVisible(!modify.get());
		});
		modify.set(false);

		bundle = AppContext.getInstance().getBundle();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PathBox.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		saveCb.disableProperty().bind(globalCb.selectedProperty().not());
		saveCb.disableProperty().addListener((o, oldVal, newVal) -> {
			if (!newVal) {
				saveCb.selectedProperty().set(false);
			}
		});

		controls.visibleProperty().bindBidirectional(modify);
		flow = Sticker.makeElemPathTextFlow(sticker.getWrapper());

		modFlow = getModFlow();
		modFlow.visibleProperty().bindBidirectional(modify);

		modifyBut.visibleProperty().bind(modify.not());

		stack.getChildren().addAll(flow, modFlow);

	}

	private TextFlow getModFlow() {

		TextFlow modFlowTemp = new TextFlow();

		for (int i = 0; i < flow.getChildren().size(); i++) {

			Node node = flow.getChildren().get(i);

			if (node instanceof AliasText) {

				TextField tf = new TextField();
				tf.textProperty().bindBidirectional(((AliasText) node).textProperty());
				initalState.put(tf, tf.getText());
				modFlowTemp.getChildren().add(tf);
				continue;
			}

			Text text = new Text();
			text.textProperty().bindBidirectional(((Text) node).textProperty());
			modFlowTemp.getChildren().add(text);

		}

		return modFlowTemp;
	}

	@FXML
	public void handleModifyBut(Event event) {

		modify.set(!modify.get());

	}

	@FXML
	public void handleCancelBut(Event event) {

		cancel();
		modify.set(false);

	}

	// TODO needs a warning prompt if there are more than 2 aliasText that are
	// getting saved or reset
	@FXML
	public void handleOKBut(Event event) {

		if (saveCb.isSelected()) {
			// save Text values in properties
			flow.getChildren().stream().filter(text -> text instanceof AliasText).forEach(text -> {

				AliasText aliasText = (AliasText) text;
				AppContext.getInstance().getProperties().set(aliasText.getWrapper().getElement().getTagName(),
						aliasText.textProperty().get());

			});
		}

		if (globalCb.isSelected()) {

			for (Node node : flow.getChildren()) {

				if (node instanceof AliasText) {

					AliasText alias = (AliasText) node;

					// TODO this should be an alias manager method called by an
					// action (action to be used in the name manager popup)
					Set<Entry<ElementWrapper, SimpleStringProperty>> entrySet = AppContext.getInstance()
							.getTagNameAliasManager().getNameMap().values().stream().map(Map::entrySet)
							.flatMap(Set::stream).collect(Collectors.toSet());

					for (Entry<ElementWrapper, SimpleStringProperty> entry : entrySet) {
						if (entry.getKey().getElement().getTagName()
								.equals(alias.getWrapper().getElement().getTagName())) {

							entry.getValue().set(alias.getText());

						}
					}

				}

			}

		}

		modify.set(false);

	}

	@FXML
	public void handleResetBut(Event event) {

		for (Node node : flow.getChildren()) {

			if (node instanceof AliasText) {

				AliasText alias = (AliasText) node;
				AppContext.getInstance().getProperties().remove(alias.getWrapper().getElement().getTagName());

				alias.textProperty().set(TagNameAliasManager.getDefaultTagNameAlias(alias.getWrapper()));

			}

		}

	}

	public void cancel() {
		if (isModify()) {
			initalState.forEach((txtField, txtValue) -> {
				txtField.setText(txtValue);
			});
		}
	}

	public final SimpleBooleanProperty modifyProperty() {
		return this.modify;
	}

	public final boolean isModify() {
		return this.modifyProperty().get();
	}

	public final void setModify(final boolean modify) {
		this.modifyProperty().set(modify);
	}

}
