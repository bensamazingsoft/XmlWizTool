package com.ben.xmlwiztool.application.wrapper.processor.impl;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.processor.WrapperProcessor;

public class ShowElementConditionnalProcessor implements WrapperProcessor {

	private String text;

	public ShowElementConditionnalProcessor(String text) {
		super();
		this.text = text;
	}

	@Override
	public void processSingle(ElementWrapper wrapper) {

		if (wrapper.match(text)) {
			wrapper.setBranchVisible(true);

		}

	}

}
