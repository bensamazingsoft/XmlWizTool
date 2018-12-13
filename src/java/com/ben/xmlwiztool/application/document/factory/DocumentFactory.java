package com.ben.xmlwiztool.application.document.factory;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.ben.xmlwiztool.application.document.exception.DocumentParsingException;
import com.ben.xmlwiztool.application.document.exception.InvalidFileFormatException;

public class DocumentFactory {

	public static Document getDocument(String source) throws DocumentParsingException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(source)));
			return doc;
		} catch (Exception e) {
			throw new DocumentParsingException();
		}

	}

	public static Document getDocument(File file)
			throws IOException, InvalidFileFormatException, DocumentParsingException {

		if (isFileXml(file)) {

			String source = FileUtils.readFileToString(file, Charset.forName("UTF-8"));
			if (source.length() > 0) {
				Document doc = getDocument(source);
				return doc;

			} else {

				throw new InvalidFileFormatException();

			}
		} else {

			throw new InvalidFileFormatException("mustBeXml");

		}

	}

	private static boolean isFileXml(File file) {
		return file.getName().matches(".*\\.xml$");
	}

}
