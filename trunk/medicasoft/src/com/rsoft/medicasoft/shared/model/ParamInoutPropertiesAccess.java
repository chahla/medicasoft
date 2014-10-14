package com.rsoft.medicasoft.shared.model;
/*
	Templates Generator
*/

/*@Author=Jean Louidort*/
/*@Generation Date=Sat Oct 26 19:38:26 EDT 2013*/
/*@Version=1.0*/

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ParamInoutPropertiesAccess extends PropertyAccess<ParamInout>  {
	@Path("entityId")
	ModelKeyProvider<ParamInout> entityId();
	@Path("key")
	ModelKeyProvider<ParamInout> key();
	
    ValueProvider<ParamInout, String> contenu();
    ValueProvider<ParamInout, String> updatedBy();
    ValueProvider<ParamInout, Date> updatedOn();
    ValueProvider<ParamInout, String> createdBy();
    ValueProvider<ParamInout, Date> createdOn();
    ValueProvider<ParamInout, String> description();
}