package com.ben.xmlwiztool.application.wrapper.processor;

import com.ben.xmlwiztool.gui.controls.viewer.ElementViewer;

public interface ViewerProcessor {

	abstract void processSingle(ElementViewer viewer);

	default void process(ElementViewer viewer) {

	}

}
