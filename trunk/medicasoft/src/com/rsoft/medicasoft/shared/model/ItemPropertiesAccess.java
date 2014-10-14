package com.rsoft.medicasoft.shared.model;

import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ItemPropertiesAccess extends PropertyAccess<Item> {
	ModelKeyProvider<Item> ID();

	LabelProvider<Item> description();
}
