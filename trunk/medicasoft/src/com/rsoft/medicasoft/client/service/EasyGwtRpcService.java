package com.rsoft.medicasoft.client.service;
/*
	Robelkend Templates Generator
*/
/*@Author=Jean Louidort*/
/*@Generation Date=Thu Apr 18 18:57:15 EDT 2013*/
/*@Version=1.0*/
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rsoft.medicasoft.shared.ConnectionException;
import com.rsoft.medicasoft.shared.DataException;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.PersistenceException;
import com.rsoft.medicasoft.shared.UserInfo;
import com.rsoft.medicasoft.shared.model.EntityKeyDescriptor;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
@RemoteServiceRelativePath( "EasyGwtRpcService" )
public interface EasyGwtRpcService extends RemoteService {
	//CRUD valable pour toutes les entites
	public <T extends ModelBase> ArrayList<T> persist(RequestDescriptor descpritor, EntityKeyDescriptor keyDescriptor, ArrayList<T> values) throws PersistenceException, ConnectionException;
	public <T extends ModelBase> T persist(RequestDescriptor descpritor, EntityKeyDescriptor keyDescriptor, T value) throws PersistenceException, ConnectionException;
	public <T extends ModelBase> ArrayList<T> persist(RequestDescriptor descpritor, ArrayList<T> values) throws PersistenceException, ConnectionException;
	public <T extends ModelBase> T persist(RequestDescriptor descpritor, T value) throws PersistenceException, ConnectionException;
	public <T extends ModelBase> ArrayList<T> search(RequestDescriptor descpritor, FilterWrapper filters) throws PersistenceException, ConnectionException;
	public <T extends ModelBase> PagingLoadResult<T> search(RequestDescriptor descpritor, PagingLoadConfig loadConfig, FilterWrapper filters) throws PersistenceException, ConnectionException;
	//Extraction de donnees sous forme de csv, valable pour toutes les entites
	public <T extends ModelBase> String extract(RequestDescriptor descpritor, T value) throws PersistenceException, ConnectionException;
	//Traitement, valide pour quelque entite
	public <T extends ModelBase> T validate(RequestDescriptor descpritor, T value) throws PersistenceException, ConnectionException;
	public <T extends ModelBase> T unvalidate(RequestDescriptor descpritor, T value) throws PersistenceException, ConnectionException;
	public <T extends ModelBase> T post(RequestDescriptor descpritor, T value) throws PersistenceException, ConnectionException;
	public <T extends ModelBase> T activate(RequestDescriptor descpritor, T value) throws PersistenceException, ConnectionException;
	public <T extends ModelBase> T deactivate(RequestDescriptor descpritor, T value) throws PersistenceException, ConnectionException;
	//Autre requetes
	public UserInfo loadUserInfos() throws DataException;
}