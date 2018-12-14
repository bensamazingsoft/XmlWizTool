package com.ben.xmlwiztool.application.wrapper.processor;

import com.ben.xmlwiztool.application.wrapper.ElementWrapper;
import com.ben.xmlwiztool.application.wrapper.impl.ComplexElementWrapper;

//provides recursive element processing
public interface WrapperProcessor {

	public void processSingle(ElementWrapper wrapper);

	default void process(ElementWrapper wrapper) {

		processSingle(wrapper);

		if (wrapper instanceof ComplexElementWrapper) {

			if (!((ComplexElementWrapper) wrapper).getChildren().isEmpty()) {

				ComplexElementWrapper elem = (ComplexElementWrapper) wrapper;

				for (ElementWrapper child : elem.getChildren()) {

					process(child);

				}

			}
		}

	}

}
