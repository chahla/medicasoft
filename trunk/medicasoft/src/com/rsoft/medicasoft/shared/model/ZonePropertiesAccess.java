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

public interface ZonePropertiesAccess extends PropertyAccess<Zone> {
	@Path("entityId")
	ModelKeyProvider<Zone> id();

	@Path("key")
	ModelKeyProvider<Zone> key();

	ValueProvider<Zone, Long> entityId();

	ValueProvider<Zone, Commune> commune();

	ValueProvider<Zone, String> nom();

	ValueProvider<Zone, String> createdBy();

	ValueProvider<Zone, Date> createdOn();

	ValueProvider<Zone, String> updatedBy();

	ValueProvider<Zone, Date> updatedOn();
}