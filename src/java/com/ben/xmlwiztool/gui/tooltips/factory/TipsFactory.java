package com.ben.xmlwiztool.gui.tooltips.factory;

import java.util.ResourceBundle;

import com.ben.xmlwiztool.application.context.AppContext;
import com.ben.xmlwiztool.gui.tooltips.Tips;

import javafx.scene.control.Tooltip;

public class TipsFactory {

	public static Tooltip getTips(Tips tip) {
		ResourceBundle bundle = AppContext.getInstance().getBundle();

		Tooltip tooltip = new Tooltip();

		switch (tip) {
		case FILTER:
			tooltip.setText(bundle.getString("tip_filter") + " "
					+ AppContext.getInstance().getProperties().get("filterSeparator"));
			break;
		case HIDE:
			tooltip.setText(bundle.getString("tip_hide"));
		}

		return tooltip;
	}

}
