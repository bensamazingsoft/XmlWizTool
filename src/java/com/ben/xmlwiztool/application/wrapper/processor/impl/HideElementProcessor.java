package com.ben.xmlwiztool.application.wrapper.processor.impl;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.processor.WrapperProcessor;

public class HideElementProcessor implements WrapperProcessor {

	@Override
	public void processSingle(ElementWrapper wrapper) {

		wrapper.setVisible(false);

	}

}
