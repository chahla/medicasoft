package com.rsoft.medicasoft.shared.model;
/*
	Robelkend Templates Generator
*/

/*@Author=Jean Louidort*/
/*@Generation Date=Wed May 01 15:53:13 EDT 2013*/
/*@Version=1.0*/

import java.util.Date;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.google.gwt.editor.client.Editor.Path;

public interface UserProfilePropertiesAccess extends PropertyAccess<UserProfileOld>  {
	@Path("rowid")
	ModelKeyProvider<UserProfileOld> rowid();
	@Path("key")
	ModelKeyProvider<UserProfileOld> key();
	
    ValueProvider<UserProfileOld, String> userId();
    ValueProvider<UserProfileOld, String> firstName();
    ValueProvider<UserProfileOld, String> lastName();
    ValueProvider<UserProfileOld, Integer> sex();
    ValueProvider<UserProfileOld, String> title();
    ValueProvider<UserProfileOld, Integer> userType();
    ValueProvider<UserProfileOld, String> language();
    ValueProvider<UserProfileOld, String> departementId();
    ValueProvider<UserProfileOld, String> createdBy();
    ValueProvider<UserProfileOld, Date> createdOn();
    ValueProvider<UserProfileOld, String> updatedBy();
    ValueProvider<UserProfileOld, Date> updatedOn();
    ValueProvider<UserProfileOld, String> idGroup();
    ValueProvider<UserProfileOld, String> succursaleId();
}