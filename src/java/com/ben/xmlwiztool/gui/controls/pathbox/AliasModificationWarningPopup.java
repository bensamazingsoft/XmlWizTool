package com.ben.xmlwiztool.gui.controls.pathbox;

import java.util.ResourceBundle;

import com.ben.xmlwiztool.application.context.AppContext;

import javafx.scene.control.Alert;

public class AliasModificationWarningPopup extends Alert {

	public AliasModificationWarningPopup() {
		super(AlertType.CONFIRMATION);

		ResourceBundle bundle = AppContext.getInstance().getBundle();

		setTitle(bundle.getString("AliasModificationWarningPopupTitle"));
		setContentText(bundle.getString("AliasModificationWarningPopupText"));
	}

}
