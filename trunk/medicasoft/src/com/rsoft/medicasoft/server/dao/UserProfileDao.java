package com.rsoft.medicasoft.server.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;

import com.googlecode.objectify.Work;
import com.rsoft.medicasoft.server.MessagesUtils;
import com.rsoft.medicasoft.shared.ExtraStus;
import com.rsoft.medicasoft.shared.PersistenceException;
import com.rsoft.medicasoft.shared.model.UserProfile;

public class UserProfileDao extends DefaultDao<UserProfile> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
							UserProfile persistentModel = searchEntityByPk(
									ofy(), true);
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
							if (!"CHANGE_PASSWORD".equalsIgnoreCase(model
									.getEXTSTATUS())) {
								model.setPinCode(persistentModel.getPinCode());
								if (ExtraStus.ACTIVATE.equalsIgnoreCase(model
										.getEXTSTATUS())) {
									model.setStatus("ACTIF");
								} else if (ExtraStus.DEACTIVATE
										.equalsIgnoreCase(model.getEXTSTATUS())) {
									model.setStatus("INACTIF");
								}
							}
							if (model.getPinCode() == null || model.getPinCode().isEmpty()) {
								model.setPinCode(model.getUserId());
							}
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
											"an entity parent key constraint fails")) {
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
		model.setPinCode(null);
	}

	@Override
	public ArrayList<UserProfile> search() throws PersistenceException {
		ArrayList<UserProfile> results = super.search();
		for (UserProfile result : results) {
			result.setPinCode(null);
		}
		return results;
	}
}
