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

public interface ProfessionPropertiesAccess extends PropertyAccess<Profession>  {
	@Path("entityId")
	ModelKeyProvider<Profession> entityId();
	@Path("key")
	ModelKeyProvider<Profession> key();
	
    ValueProvider<Profession, String> description();
    ValueProvider<Profession, String> classification();
    ValueProvider<Profession, String> createdBy();
    ValueProvider<Profession, Date> createdOn();
    ValueProvider<Profession, String> updatedBy();
    ValueProvider<Profession, Date> updatedOn();
}