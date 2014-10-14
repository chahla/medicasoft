package com.rsoft.medicasoft.shared.model;
/*
	Templates Generator
*/

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Nov 18 01:14:09 EST 2013*/
/*@Version=1.0*/

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ReligionPropertiesAccess extends PropertyAccess<Religion>  {
	@Path("entityId")
	ModelKeyProvider<Religion> entityId();
	@Path("key")
	ModelKeyProvider<Religion> key();
	ValueProvider<Religion, String> description();
    ValueProvider<Religion, Item> classificationItem();
    ValueProvider<Religion, String> classification();
    ValueProvider<Religion, String> createdBy();
    ValueProvider<Religion, Date> createdOn();
    ValueProvider<Religion, String> updatedBy();
    ValueProvider<Religion, Date> updatedOn();
}