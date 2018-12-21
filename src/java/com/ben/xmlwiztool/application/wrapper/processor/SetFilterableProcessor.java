package com.ben.xmlwiztool.application.wrapper.processor;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;

public class SetFilterableProcessor implements WrapperProcessor {

	// TODO checkbox must trigger hide all and true-tree-visibility toggle from
	// non empty single element
	@Override
	public void processSingle(ElementWrapper wrapper) {

		if (!wrapper.isFilterable()) {
			wrapper.setBranchFilterable(false);
		}

	}

}
