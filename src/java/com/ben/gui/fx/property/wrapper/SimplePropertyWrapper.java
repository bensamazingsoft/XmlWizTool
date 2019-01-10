package com.ben.gui.fx.property.wrapper;

import com.ben.reinitializable.Reinitializable;

import javafx.beans.property.Property;

public abstract class SimplePropertyWrapper<P extends Property<T>, T> implements Reinitializable {

	P	property;
	T	initialValue;

	public SimplePropertyWrapper(P property) {
		super();
		this.property = property;
		initialValue = get();

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

	@Override
	public void reinit() {
		set(initialValue);

	}

}
