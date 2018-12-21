package com.ben.xmlwiztool.application.wrapper.processor;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

public class SetFilterableProcessor implements WrapperProcessor {

	@Override
	public void processSingle(ElementWrapper wrapper) {

		if (!wrapper.isFilterable()) {
			wrapper.setBranchFilterable(false);
		}

	}

}
