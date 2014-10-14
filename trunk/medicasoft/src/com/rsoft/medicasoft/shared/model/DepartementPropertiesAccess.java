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

public interface DepartementPropertiesAccess extends PropertyAccess<Departement>  {
	@Path("entityId")
	ModelKeyProvider<Departement> id();
	@Path("key")
	ModelKeyProvider<Departement> key();
	
    
	ValueProvider<Departement, Long> entityId();
    ValueProvider<Departement, String> nom();
    ValueProvider<Departement, Pays> pays();
    ValueProvider<Departement, Date> createdOn();
    ValueProvider<Departement, String> updatedBy();
    ValueProvider<Departement, Date> updatedOn();
    ValueProvider<Departement, String> createdBy();
}