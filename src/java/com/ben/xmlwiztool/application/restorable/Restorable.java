package com.ben.xmlwiztool.application.restorable;

import javafx.beans.property.Property;

public class Restorable<T extends Property<Y>, Y> {

	private final Property<Y> property;

	public Restorable(Property<Y> property) {
		super();
		this.property = property;
	}

	public Memento<Y> save() {

		return new Memento<>(property.getValue());

	}

	public void restore(Memento<Y> memento) {
		property.setValue(memento.getState());
	}

	public static class Memento<Y> {

		private Y state;

		public Memento(Y state) {

			this.state = state;
		}

		private Y getState() {
			return state;
		}

	}

	public final Property<Y> property() {
		return this.property;
	}

	public final Y get() {
		return this.property().getValue();
	}

	public final void set(final Y property) {
		this.property().setValue(property);
	}

}
