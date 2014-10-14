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

public interface CommunePropertiesAccess extends PropertyAccess<Commune> {
	@Path("entityId")
	ModelKeyProvider<Commune> id();

	@Path("key")
	ModelKeyProvider<Commune> key();

	ValueProvider<Commune, Long> entityId();

	ValueProvider<Commune, String> nom();

	ValueProvider<Commune, Departement> departement();

	ValueProvider<Commune, Date> createdOn();

	ValueProvider<Commune, String> updatedBy();

	ValueProvider<Commune, Date> updatedOn();

	ValueProvider<Commune, String> createdBy();
}