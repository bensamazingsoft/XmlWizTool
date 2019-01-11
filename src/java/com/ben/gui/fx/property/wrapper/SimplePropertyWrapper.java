package com.ben.gui.fx.property.wrapper;

import com.ben.gui.fx.node.FxNode;
import com.ben.reinitializable.Reinitializable;

import javafx.beans.property.Property;
import javafx.scene.Node;

public abstract class SimplePropertyWrapper<P extends Property<T>, T> implements Reinitializable, FxNode<Node> {

	P						property;
	T						initialValue;
	private final String	label;

	public SimplePropertyWrapper(P property) {
		super();
		this.property = property;
		initialValue = get();
		label = "";

	}

	public SimplePropertyWrapper(P property, String label) {
		super();
		initialValue = get();
		this.label = label;
	}

	public T get() {
		return property.getValue();
	}

	public void set(T value) {
		property.setValue(value);
	}

	public P property() {
		return property;
	}

	protected String label() {
		return this.label;
	}

	@Override
	public void reinit() {
		set(initialValue);

	}

}
