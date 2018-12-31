package com.ben.xmlwiztool.application.wrapper.processor.impl;

import java.util.HashMap;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.application.tagname.aliaser.TagNameAliasManager;
import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.processor.WrapperProcessor;

import javafx.beans.property.SimpleStringProperty;

public class AliasesProcessor implements WrapperProcessor {

	private ElementWrapper root;

	public AliasesProcessor(ElementWrapper root) {

		this.root = root;
		AppContext.getInstance().getTagNameAliasManager().getNameMap().put(root,
				new HashMap<ElementWrapper, SimpleStringProperty>());

	}

	@Override
	public void processSingle(ElementWrapper wrapper) {
		if (TagNameAliasManager.aliasable(wrapper)) {

			TagNameAliasManager aliasMgr = AppContext.getInstance().getTagNameAliasManager();
			aliasMgr.getNameMap().get(root).put(wrapper,
					new SimpleStringProperty(TagNameAliasManager.getDefaultTagNameAlias(wrapper)));

		}

	}

}
