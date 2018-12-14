package com.ben.xmlwiztool.application.executor;

import java.util.ArrayList;
import java.util.List;

import com.ben.xmlwiztool.application.actions.IAction;

public class Executor {

	private static Executor instance;
	private static List<IAction> history;

	private Executor() {
		history = new ArrayList<>();
	}

	public static void execute(IAction action) {

		history.add(action);
		action.execute();

	}

	public static Executor getInstance() {

		if (instance == null) {
			instance = new Executor();
		}

		return instance;
	}

}
