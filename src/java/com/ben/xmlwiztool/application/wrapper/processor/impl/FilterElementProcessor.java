package com.ben.xmlwiztool.application.wrapper.processor.impl;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.processor.WrapperProcessor;

public class FilterElementProcessor implements WrapperProcessor {

	private String text;

	public FilterElementProcessor(String text) {
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
