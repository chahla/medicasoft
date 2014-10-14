package com.rsoft.medicasoft.server.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.ArrayList;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.cmd.Query;
import com.rsoft.medicasoft.server.MessagesUtils;
import com.rsoft.medicasoft.shared.FilterWrapper;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.PersistenceException;
import com.rsoft.medicasoft.shared.model.UserProfile;

public class DefaultDao<T extends ModelBase> extends Dao<T> implements
		Serializable {
	
	protected static final long serialVersionUID = 1L;
	protected T model;
	protected UserProfile userProfile;
	protected FilterWrapper filters;
	private Class<T> modelClass;
	private String modelName;

	@Override
	public void insert() throws PersistenceException {
		if (model == null) {
			throw new PersistenceException(MessagesUtils.getMessage(
					userProfile, "unexpectedError_nullModel",
					"Internal error. The model is null."));
		}
		PersistenceException exception = ofy().transact(
				new Work<PersistenceException>() {
					public PersistenceException run() {
						PersistenceException exception = null;
						try {
							DaoTools.finishObject(model, userProfile,
									entityKeyDescriptor, "PERSIST");
							ofy().save().entity(model).now();
						} catch (Exception ex) {
							ex.printStackTrace(System.out);// To be removed
							T x = searchEntityByPk(ofy());
							if (x != null) {
								exception = new PersistenceException(
										MessagesUtils

										.getMessage(userProfile, "recordFound",
												"Record is already existed."),
										ex, true);
							} else {
								exception = new PersistenceException(
										MessagesUtils
												.getMessage(
														userProfile,
														"unexpectedError_insertRecord",
														"Unexpected error inserting record."),
										ex, true);
							}
						} finally {
						}
						return exception;
					}
				});
		if (exception != null) {
			throw exception;
		}
	}

	@Override
	public void merge() throws PersistenceException {
		if (model == null) {
			throw new PersistenceException(MessagesUtils.getMessage(
					userProfile, "unexpectedError_nullModel",
					"Internal error. The model is null."), null, true);
		}
		PersistenceException exception = ofy().transact(
				new Work<PersistenceException>() {
					public PersistenceException run() {
						PersistenceException exception = null;
						try {
							T persistentModel = searchEntityByPk(ofy(), true);
							if (persistentModel == null) {
								throw new PersistenceException(MessagesUtils
										.getMessage(userProfile,
												"recordNotFound",
												"Record not found."));
							}
							if (persistentModel.getRowscn() != model
									.getRowscn()) {
								throw new PersistenceException(
										MessagesUtils
												.getMessage(
														userProfile,
														"recordIsLocked",
														"Record is updating by an other person. Please look at the record modification first before modify it. Merging Count:(Current/Old: "
																+ model.getRowscn()
																+ "/"
																+ persistentModel
																		.getRowscn()
																+ ")"), null,
										true);
							}
							DaoTools.finishObject(model, userProfile,
									entityKeyDescriptor, "MERGE");
							ofy().save().entity(model).now();
						} catch (PersistenceException ex) {
							ex.printStackTrace(System.out);// To be removed
							model.setRowscn(model.getRowscn() != null ? model
									.getRowscn() - 1 : null);
							exception = ex;
						} catch (Exception ex) {
							ex.printStackTrace(System.out);// To be removed
							model.setRowscn(model.getRowscn() != null ? model
									.getRowscn() - 1 : null);
							if (ex.toString()
									.toLowerCase()
									.contains(
											"a entity parent key constraint fails")) {
								exception = new PersistenceException(
										MessagesUtils.getMessage(userProfile,
												"cannotModifyParent_key",
												"Unexpected error removing autre revenu. Cause: "
														+ ex), ex, true);
							}
							exception = new PersistenceException(MessagesUtils
									.getMessage(userProfile,
											"unexpectedError_modifyRecord",
											"Unexpected error updating autre revenu. Cause: "
													+ ex), true, ex);
						} finally {
						}
						return exception;
					}
				});
		if (exception != null) {
			throw exception;
		}
	}

	@Override
	public void remove() throws PersistenceException {
		if (model == null) {
			throw new PersistenceException(MessagesUtils.getMessage(
					userProfile, "unexpectedError_nullModel",
					"Internal error. The model is null."));
		}
		PersistenceException exception = ofy().transact(
				new Work<PersistenceException>() {
					public PersistenceException run() {
						PersistenceException exception = null;
						try {
							// Ne supprime pas l'entite s'il est entrain d'etre
							// modifie par
							// quelqu'un d'autre
							T persistentModel = searchEntityByPk(ofy(), true);
							if (persistentModel == null) {
								throw new PersistenceException(MessagesUtils
										.getMessage(userProfile,
												"recordNotFound",
												"Record not found."));
							}
							if (persistentModel.getRowscn() != model
									.getRowscn()) {
								throw new PersistenceException(
										MessagesUtils
												.getMessage(
														userProfile,
														"recordIsLocked",
														"Record is updating by an other person. Please look at the record modification first before modify it. Merging Count:(Current/Old: "
																+ model.getRowscn()
																+ "/"
																+ persistentModel
																		.getRowscn()
																+ ")"), null,
										true);
							}
							DaoTools.finishObject(model, userProfile,
									entityKeyDescriptor, "REMOVE");
							ofy().delete().entity(model).now();
						} catch (PersistenceException ex) {
							ex.printStackTrace(System.out);// To be removed
							model.setRowscn(model.getRowscn() != null ? model
									.getRowscn() - 1 : null);
							exception = ex;
						} catch (Exception ex) {
							ex.printStackTrace(System.out);// To be removed
							model.setRowscn(model.getRowscn() != null ? model
									.getRowscn() - 1 : null);
							if (ex.toString()
									.toLowerCase()
									.contains(
											"a entity parent key constraint fails")) {
								exception = new PersistenceException(
										MessagesUtils.getMessage(userProfile,
												"cannotModifyParent_key",
												"Unexpected error removing autre revenu. Cause: "
														+ ex), ex, true);
							}
							exception = new PersistenceException(MessagesUtils
									.getMessage(userProfile,
											"unexpectedError_modifyRecord",
											"Unexpected error updating autre revenu. Cause: "
													+ ex), true, ex);
						} finally {
						}
						return exception;
					}
				});
		if (exception != null) {
			throw exception;
		}
	}

	@Override
	public ArrayList<T> search() throws PersistenceException {
		try {
			ArrayList<T> results = findEntities(ofy(), true, -1, -1);
			return results;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			throw new PersistenceException(MessagesUtils.getMessage(
					userProfile, "unexpectedError_searchRecords",
					"Unexpected error searching T. Cause: " + ex), true, ex);
		} finally {
		}
	}

	public ArrayList<T> findEntities(Objectify ofy) {
		return findEntities(ofy, true, -1, -1);
	}

	public ArrayList<T> findEntities(Objectify ofy, int maxResults,
			int firstResult) {
		return findEntities(ofy, false, maxResults, firstResult);
	}

	@Override
	public void setFilters(FilterWrapper filters) {
		this.filters = filters;
	}

	protected ArrayList<T> findEntities(Objectify ofy, boolean all,
			int maxResults, int firstResult) {
		Query<T> query = ofy().load().type(this.modelClass);
		query = FilterTools.addCriterias(query, filters);
		return new ArrayList<T>(query.list());
	}
 
	@Override
	public void setModel(T model) throws PersistenceException {
		this.model = model;
	}

	@Override
	public T getModel() {
		return model;
	}

	@Override
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	// Search without locking the record
	protected T searchEntityByPk(Objectify ofy) {
		return searchEntityByPk(ofy, false);
	}

	// Search and lock the record
	protected T searchEntityByPk(Objectify ofy, boolean lock) {
		if (model == null || this.modelClass == null
				|| model.getEntityId() == null) {
			return null;
		}
		Key<T> key = Key.create(modelClass, model.getEntityId());
		return ofy().load().key(key).now();
	}

	@Override
	public void setModelClass(Class<T> modelClass) {
		this.modelClass = modelClass;
		if (this.modelClass != null) {
			this.modelName = this.modelClass.getCanonicalName();
		}
	}

	@Override
	public String getModelClass() {
		return modelName;
	}
}
