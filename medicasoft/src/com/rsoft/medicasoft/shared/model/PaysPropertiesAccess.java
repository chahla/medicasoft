package com.rsoft.medicasoft.shared.model;
/*
	Templates Generator
*/

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Oct 14 22:30:48 EDT 2013*/
/*@Version=1.0*/

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface PaysPropertiesAccess extends PropertyAccess<Pays>  {
	@Path("entityId")
	ModelKeyProvider<Pays> id();
	
	@Path("key")
	ModelKeyProvider<Pays> key();
	
    ValueProvider<Pays, Long> entityId();
    ValueProvider<Pays, String> code();
    ValueProvider<Pays, String> nom();
    ValueProvider<Pays, Date> createdOn();
    ValueProvider<Pays, String> updatedBy();
    ValueProvider<Pays, Date> updatedOn();
    ValueProvider<Pays, String> createdBy();
}