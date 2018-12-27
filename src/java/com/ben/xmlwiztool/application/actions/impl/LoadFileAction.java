package com.ben.xmlwiztool.application.actions.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.exception.popup.ExceptionPopUp;
import com.ben.xmlwiztool.application.executor.Executor;

import javafx.stage.FileChooser;

public class LoadFileAction implements IAction {

	@Override
	public void execute() {
		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle(AppContext.getInstance().getBundle().getString("fileChooserTitle"));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML", "*.xml"),
				new FileChooser.ExtensionFilter("HTML", "*.html"), new FileChooser.ExtensionFilter("XHTML", "*.xhtml"));

		File file = fileChooser.showOpenDialog(null);
		if (file != null) {

			List<String> sources = new ArrayList<>();
			try {

				sources = Files.readAllLines(file.toPath());

				String source = String.join("\n", sources);

				Executor.getInstance().execute(new LoadStringSourceAction(source));

			} catch (IOException e) {
				new ExceptionPopUp(e);
			}

		}

	}

}
