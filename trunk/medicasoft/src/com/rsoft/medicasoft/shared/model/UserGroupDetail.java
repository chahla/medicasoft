package com.rsoft.medicasoft.shared.model;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Wed Sep 11 20:34:23 EDT 2013*/
/*@Version=1.0*/
import java.io.Serializable;

import com.googlecode.objectify.annotation.Embed;
import com.googlecode.objectify.annotation.Index;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelException;

@Embed
@Index
public class UserGroupDetail extends ModelBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String formId;

	private String availableActions;

	private Boolean canVisualize;

	private Boolean canCreate;

	private Boolean canUpdate;

	private Boolean canRemove;

	private Boolean canValidate;

	private Boolean canPost;

	private Boolean canCancel;

	private Boolean canPay;

	private Boolean canActivate;

	private Boolean canDeactivate;

	private Boolean canClose;

	private Boolean canFinalize;

	public UserGroupDetail() {
	}

	public void initAttributes(boolean value) {
		canVisualize = value;
		canCreate = value;
		canUpdate = value;
		canRemove = value;
		canValidate = value;
		canPost = value;
		canCancel = value;
		canPay = value;
		canActivate = value;
		canDeactivate = value;
		canClose = value;
		canFinalize = value;
	}

	public String getAvailableActions() {
		return availableActions;
	}

	public void setAvailableActions(String availableActions) {
		this.availableActions = availableActions;
	}

	/**
	 * Get the value of formId
	 * 
	 * @return the value of formId Get the value formId
	 */
	public String getFormId() {
		return this.formId;
	}

	/**
	 * Get the value of canVisualize
	 * 
	 * @return the value of canVisualize Get the value canVisualize
	 */
	public Boolean getCanVisualize() {
		return this.canVisualize;
	}

	/**
	 * Get the value of canCreate
	 * 
	 * @return the value of canCreate Get the value canCreate
	 */
	public Boolean getCanCreate() {
		return this.canCreate;
	}

	/**
	 * Get the value of canUpdate
	 * 
	 * @return the value of canUpdate Get the value canUpdate
	 */
	public Boolean getCanUpdate() {
		return this.canUpdate;
	}

	/**
	 * Get the value of canRemove
	 * 
	 * @return the value of canRemove Get the value canRemove
	 */
	public Boolean getCanRemove() {
		return this.canRemove;
	}

	/**
	 * Get the value of canValidate
	 * 
	 * @return the value of canValidate Get the value canValidate
	 */
	public Boolean getCanValidate() {
		return this.canValidate;
	}

	/**
	 * Get the value of canPost
	 * 
	 * @return the value of canPost Get the value canPost
	 */
	public Boolean getCanPost() {
		return this.canPost;
	}

	/**
	 * Get the value of canCancel
	 * 
	 * @return the value of canCancel Get the value canCancel
	 */
	public Boolean getCanCancel() {
		return this.canCancel;
	}

	/**
	 * Get the value of canPay
	 * 
	 * @return the value of canPay Get the value canPay
	 */
	public Boolean getCanPay() {
		return this.canPay;
	}

	/**
	 * Get the value of canActivate
	 * 
	 * @return the value of canActivate Get the value canActivate
	 */
	public Boolean getCanActivate() {
		return this.canActivate;
	}

	/**
	 * Get the value of canDeactivate
	 * 
	 * @return the value of canDeactivate Get the value canDeactivate
	 */
	public Boolean getCanDeactivate() {
		return this.canDeactivate;
	}

	/**
	 * Get the value of canClose
	 * 
	 * @return the value of canClose Get the value canClose
	 */
	public Boolean getCanClose() {
		return this.canClose;
	}

	/**
	 * Get the value of canFinalize
	 * 
	 * @return the value of canFinalize Get the value canFinalize
	 */
	public Boolean getCanFinalize() {
		return this.canFinalize;
	}

	/**
	 * Set the value of formId
	 * 
	 * @param formId
	 *            * new value of formId
	 */
	public void setFormId(String formId) {
		if (this.compareFields(this.formId, formId)) {
			this.fireUpdatePendingChanged(true);
		}
		this.formId = formId != null ? formId.trim() : null;
	}

	/**
	 * Set the value of canVisualize
	 * 
	 * @param canVisualize
	 *            * new value of canVisualize
	 */
	public void setCanVisualize(Boolean canVisualize) {
		if (this.compareFields(this.canVisualize, canVisualize)) {
			this.fireUpdatePendingChanged(true);
		}
		this.canVisualize = canVisualize;
		if (this.isUpdatePending() && !this.canVisualize) {
			this.initAttributes(this.canVisualize);
		}
	}

	/**
	 * Set the value of canCreate
	 * 
	 * @param canCreate
	 *            * new value of canCreate
	 */
	public void setCanCreate(Boolean canCreate) {
		if (this.compareFields(this.canCreate, canCreate)) {
			this.fireUpdatePendingChanged(true);
		}
		this.canCreate = canCreate;
	}

	/**
	 * Set the value of canUpdate
	 * 
	 * @param canUpdate
	 *            * new value of canUpdate
	 */
	public void setCanUpdate(Boolean canUpdate) {
		if (this.compareFields(this.canUpdate, canUpdate)) {
			this.fireUpdatePendingChanged(true);
		}
		this.canUpdate = canUpdate;
	}

	/**
	 * Set the value of canRemove
	 * 
	 * @param canRemove
	 *            * new value of canRemove
	 */
	public void setCanRemove(Boolean canRemove) {
		if (this.compareFields(this.canRemove, canRemove)) {
			this.fireUpdatePendingChanged(true);
		}
		this.canRemove = canRemove;
	}

	/**
	 * Set the value of canValidate
	 * 
	 * @param canValidate
	 *            * new value of canValidate
	 */
	public void setCanValidate(Boolean canValidate) {
		if (this.compareFields(this.canValidate, canValidate)) {
			this.fireUpdatePendingChanged(true);
		}
		this.canValidate = canValidate;
	}

	/**
	 * Set the value of canPost
	 * 
	 * @param canPost
	 *            * new value of canPost
	 */
	public void setCanPost(Boolean canPost) {
		if (this.compareFields(this.canPost, canPost)) {
			this.fireUpdatePendingChanged(true);
		}
		this.canPost = canPost;
	}

	/**
	 * Set the value of canCancel
	 * 
	 * @param canCancel
	 *            * new value of canCancel
	 */
	public void setCanCancel(Boolean canCancel) {
		if (this.compareFields(this.canCancel, canCancel)) {
			this.fireUpdatePendingChanged(true);
		}
		this.canCancel = canCancel;
	}

	/**
	 * Set the value of canPay
	 * 
	 * @param canPay
	 *            * new value of canPay
	 */
	public void setCanPay(Boolean canPay) {
		if (this.compareFields(this.canPay, canPay)) {
			this.fireUpdatePendingChanged(true);
		}
		this.canPay = canPay;
	}

	/**
	 * Set the value of canActivate
	 * 
	 * @param canActivate
	 *            * new value of canActivate
	 */
	public void setCanActivate(Boolean canActivate) {
		if (this.compareFields(this.canActivate, canActivate)) {
			this.fireUpdatePendingChanged(true);
		}
		this.canActivate = canActivate;
	}

	/**
	 * Set the value of canDeactivate
	 * 
	 * @param canDeactivate
	 *            * new value of canDeactivate
	 */
	public void setCanDeactivate(Boolean canDeactivate) {
		if (this.compareFields(this.canDeactivate, canDeactivate)) {
			this.fireUpdatePendingChanged(true);
		}
		this.canDeactivate = canDeactivate;
	}

	/**
	 * Set the value of canClose
	 * 
	 * @param canClose
	 *            * new value of canClose
	 */
	public void setCanClose(Boolean canClose) {
		if (this.compareFields(this.canClose, canClose)) {
			this.fireUpdatePendingChanged(true);
		}
		this.canClose = canClose;
	}

	/**
	 * Set the value of canFinalize
	 * 
	 * @param canFinalize
	 *            * new value of canFinalize
	 */
	public void setCanFinalize(Boolean canFinalize) {
		if (this.compareFields(this.canFinalize, canFinalize)) {
			this.fireUpdatePendingChanged(true);
		}
		this.canFinalize = canFinalize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		if (this.getEntityId() != null) {
			result += ((this.getEntityId() == null) ? 0 : this.getEntityId()
					.hashCode());
		}
		if (this.getLineNo() != null) {
			result += ((this.getLineNo() == null) ? 0 : this.getLineNo()
					.hashCode());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserGroupDetail other = (UserGroupDetail) obj;
		// Je fais de comparaison par id dans le cas de mise a jour ou de
		// suppression
		// dans le cas d'insertion je le fais par lineNo
		if (this.getEntityId() != null && other.getEntityId() != null
				&& !this.getEntityId().equals(other.getEntityId())) {
			return false;
		}
		if (this.getLineNo() == null) {
			if (other.getLineNo() != null)
				return false;
		} else if (other.getLineNo() == null) {
			if (this.getLineNo() != null)
				return false;
		} else if (this.getLineNo() != other.getLineNo()) {
			return false;
		}
		return true;
	}

	// Recuperer la signature de l'entite
	public String getKey() {
		StringBuilder builder = new StringBuilder();
		if (getLineNo() != null) {
			builder.append(getLineNo());
		} else if (this.getEntityId() != null) {
			builder.append(getEntityId());
		}
		return builder.toString();
	}

	public void merge(ModelBase modelBase) {
		UserGroupDetail model = (UserGroupDetail) modelBase;
		setEntityId(model.getEntityId());
		setErrorMessage(model.getErrorMessage());
		setCreatedBy(model.getCreatedBy());
		setCreatedOn(model.getCreatedOn());
		setUpdatedBy(model.getUpdatedBy());
		setUpdatedOn(model.getUpdatedOn());
		setRowscn(model.getRowscn());
		setRowscn(model.getRowscn());
		formId = model.getFormId();
		canVisualize = model.getCanVisualize();
		canCreate = model.getCanCreate();
		canUpdate = model.getCanUpdate();
		canRemove = model.getCanRemove();
		canValidate = model.getCanValidate();
		canPost = model.getCanPost();
		canCancel = model.getCanCancel();
		canPay = model.getCanPay();
		canActivate = model.getCanActivate();
		canDeactivate = model.getCanDeactivate();
		canClose = model.getCanClose();
		canFinalize = model.getCanFinalize();
	}

	@Override
	public void validateModel() throws ModelException {
		StringBuilder builder = new StringBuilder();
		if (formId == null || formId.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("formId");
			} else {
				builder.append("|formId");
			}

		}
		if (!builder.toString().trim().isEmpty()) {
			throw new ModelException("MISSING_FIELDS", builder.toString(),
					"Fill all required fields before continue");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (this.getEntityId() != null) {
			return Long.toString(this.getEntityId());
		}
		if (this.getEntityId() != null) {
			return Long.toString(this.getEntityId());
		}
		if (this.getLineNo() != null) {
			return Long.toString(this.getLineNo());
		}
		return super.toString();
	}

	@Override
	public Object getPrimaryKey() {
		return this.getEntityId();
	}

	public static UserGroupDetail parse(String value) {
		UserGroupDetail model = new UserGroupDetail();
		return model;
	}
}