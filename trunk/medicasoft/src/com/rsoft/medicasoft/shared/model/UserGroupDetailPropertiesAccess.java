package com.rsoft.medicasoft.shared.model;
/*
	Templates Generator
*/

/*@Author=Jean Louidort*/
/*@Generation Date=Wed Sep 11 20:58:38 EDT 2013*/
/*@Version=1.0*/

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface UserGroupDetailPropertiesAccess extends PropertyAccess<UserGroupDetail>  {
	@Path("entityId")
	ModelKeyProvider<UserGroupDetail> entityId();
	@Path("key")
	ModelKeyProvider<UserGroupDetail> key();
    ValueProvider<UserGroupDetail, String> formId();
    ValueProvider<UserGroupDetail, Boolean> canVisualize();
    ValueProvider<UserGroupDetail, Boolean> canCreate();
    ValueProvider<UserGroupDetail, Boolean> canUpdate();
    ValueProvider<UserGroupDetail, Boolean> canRemove();
    ValueProvider<UserGroupDetail, Boolean> canValidate();
    ValueProvider<UserGroupDetail, Boolean> canPost();
    ValueProvider<UserGroupDetail, Boolean> canCancel();
    ValueProvider<UserGroupDetail, Boolean> canPay();
    ValueProvider<UserGroupDetail, Boolean> canActivate();
    ValueProvider<UserGroupDetail, Boolean> canDeactivate();
    ValueProvider<UserGroupDetail, Boolean> canClose();
    ValueProvider<UserGroupDetail, Boolean> canFinalize();
    ValueProvider<UserGroupDetail, String> createdBy();
    ValueProvider<UserGroupDetail, Date> createdOn();
    ValueProvider<UserGroupDetail, String> updatedBy();
    ValueProvider<UserGroupDetail, Date> updatedOn();
}