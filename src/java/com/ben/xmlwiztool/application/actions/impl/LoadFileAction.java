package com.ben.xmlwiztool.application.actions.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import com.ben.xmlwiztool.application.actions.IAction;
import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.document.exception.DocumentParsingException;
import com.ben.xmlwiztool.application.document.factory.DocumentFactory;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.factory.ElementWrapperFactory;
import com.ben.xmlwiztool.application.wrapper.processor.impl.AliasesProcessor;
import com.ben.xmlwiztool.gui.controls.tab.WizTab;
import com.ben.xmlwiztool.gui.facade.GuiFacade;

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

				Document doc = DocumentFactory.getDocument(source);

				ElementWrapper wrapper = ElementWrapperFactory.getElementWrapper(doc.getDocumentElement());

				AliasesProcessor processor = new AliasesProcessor(wrapper);
				processor.process(wrapper);

				GuiFacade.getInstance().getTabPane().getTabs().add(new WizTab(wrapper));
			} catch (IOException | DocumentParsingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
