package com.rsoft.medicasoft.shared.model;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Tue Jul 30 16:17:59 EDT 2013*/
/*@Version=1.0*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Load;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelBaseX;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.PersistenceException;

@Entity
public class UserGroup extends ModelBaseX implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;
	private String description;
	@Load
	private List<UserGroupDetail> userGroupDetails = new ArrayList<UserGroupDetail>();

	public UserGroup() {
	}

	public UserGroupDetail addUserGroupDetail(UserGroupDetail userGroupDetail) {
		if (userGroupDetail != null) {
			int index = userGroupDetails.indexOf(userGroupDetail);
			if (index >= 0) {// L'entite existe de deja fait un merging
				UserGroupDetail dummy = userGroupDetails.get(index);
				dummy.merge(userGroupDetail);
			} else {// Dans le cas contraire met l'entityId et ajoute le dans la
					// liste
				if (userGroupDetail.getEntityId() == null) {
					userGroupDetail.setEntityId(new Long(userGroupDetails
							.size() + 1));
				}
				userGroupDetails.add(userGroupDetail);
			}
			setUpdatePending(true);
		}
		return userGroupDetail;
	}

	public void removeUserGroupDetail(UserGroupDetail userGroupDetail) {
		if (userGroupDetail != null) {
			userGroupDetails.remove(userGroupDetail);
			setUpdatePending(true);
		}
	}

	public List<UserGroupDetail> getUserGroupDetails() {
		return userGroupDetails;
	}

	public void setUserGroupDetails(List<UserGroupDetail> userGroupDetails) {
		this.userGroupDetails = userGroupDetails;
	}

	/**
	 * Get the value of type
	 * 
	 * @return the value of type Get the value type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Get the value of description
	 * 
	 * @return the value of description Get the value description
	 */
	public String getDescription() {
		return this.description;
	}

	/*
	 * public void setUserGroupDetail(UserGroupDetail userGroupDetail) {
	 * this.userGroupDetail = userGroupDetail; }
	 *//**
	 * Set the value of type
	 * 
	 * @param type
	 *            * new value of type
	 */
	public void setType(String type) {
		if (this.compareFields(this.type, type)) {
			this.fireUpdatePendingChanged(true);
		}
		this.type = type != null ? type.trim() : null;
	}

	/**
	 * Set the value of description
	 * 
	 * @param description
	 *            * new value of description
	 */
	public void setDescription(String description) {
		if (this.compareFields(this.description, description)) {
			this.fireUpdatePendingChanged(true);
		}
		this.description = description != null ? description.trim() : null;
	}

	@PostLoad
	public void afterLoad() throws PersistenceException {
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
		UserGroup other = (UserGroup) obj;
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

	@PrePersist
	public void beforePersist() throws PersistenceException {
		try {
			validateModel();
		} catch (ModelException ex) {
			throw new PersistenceException(ex + ". Fields: "
					+ ex.getParameters());
		}
	}

	// Recuperer la signature de l'entite
	public String getKey() {
		StringBuilder builder = new StringBuilder();
		if (getLineNo() != null) {
			builder.append(getLineNo());
		}

		if (type != null) {
			builder.append(type);
		}
		if (description != null) {
			builder.append(description);
		}
		return builder.toString();
	}

	public void merge(ModelBase modelBase) {
		UserGroup model = (UserGroup) modelBase;
		setEntityId(model.getEntityId());
		setErrorMessage(model.getErrorMessage());
		setCreatedBy(model.getCreatedBy());
		setCreatedOn(model.getCreatedOn());
		setUpdatedBy(model.getUpdatedBy());
		setUpdatedOn(model.getUpdatedOn());
		setRowscn(model.getRowscn());
		setRowscn(model.getRowscn());
		type = model.getType();
		description = model.getDescription();
	}

	@Override
	public void validateModel() throws ModelException {
		StringBuilder builder = new StringBuilder();

		if (description == null || description.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("description");
			} else {
				builder.append("|description");
			}
		}

		if (type == null || type.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("type");
			} else {
				builder.append("|type");
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
		if (this.description != null) {
			return this.description;
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

	public static UserGroup parse(String value) {
		UserGroup model = new UserGroup();
		// Set PkValue to complete this class

		return model;
	}
}