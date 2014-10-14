package com.rsoft.medicasoft.shared.model;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Tue Jul 30 16:21:25 EDT 2013*/
/*@Version=1.0*/

import java.util.Date;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.google.gwt.editor.client.Editor.Path;

public interface UserGroupPropertiesAccess extends PropertyAccess<UserGroup> {
	@Path("entityId")
	ModelKeyProvider<UserGroup> entityId();

	@Path("key")
	ModelKeyProvider<UserGroup> key();

	LabelProvider<String> typex();

	ValueProvider<UserGroup, String> type();

	ValueProvider<UserGroup, String> description();

	ValueProvider<UserGroup, String> createdBy();

	ValueProvider<UserGroup, Date> createdOn();

	ValueProvider<UserGroup, String> updatedBy();

	ValueProvider<UserGroup, Date> updatedOn();
}