package com.rsoft.medicasoft.shared.model;

/*
 Robelkend Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Wed May 01 15:54:25 EDT 2013*/
/*@Version=1.0*/
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelBaseX;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.PersistenceException;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfileOld extends ModelBaseX implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * @JoinColumn(name = "ID_GROUP", referencedColumnName = "ID_GROUP",
	 * insertable = false, updatable = false)
	 * 
	 * @ManyToOne(optional = false) private UserGroup userGroup;
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "USER_ID")
	private String userId;

	@Basic(optional = false)
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Basic(optional = false)
	@Column(name = "LAST_NAME")
	private String lastName;

	@Basic(optional = true)
	@Column(name = "SEX")
	private Integer sex;

	@Basic(optional = false)
	@Column(name = "TITLE")
	private String title;

	@Basic(optional = false)
	@Column(name = "USER_TYPE")
	private Integer userType;

	@Basic(optional = false)
	@Column(name = "LANGUAGE")
	private String language;

	@Basic(optional = true)
	@Column(name = "DEPARTEMENT_ID")
	private String departementId;

	@Basic(optional = true)
	@Column(name = "ID_GROUP")
	private String idGroup;

	@Basic(optional = false)
	@Column(name = "SUCCURSALE_ID")
	private String succursaleId;

	public UserProfileOld() {
	}

	/*
	 * public UserGroup getUserGroup() { return this.userGroup; }
	 *//**
	 * Get the value of userId
	 * 
	 * @return the value of userId Get the value userId
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Get the value of firstName
	 * 
	 * @return the value of firstName Get the value firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Get the value of lastName
	 * 
	 * @return the value of lastName Get the value lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Get the value of sex
	 * 
	 * @return the value of sex Get the value sex
	 */
	public Integer getSex() {
		return this.sex;
	}

	/**
	 * Get the value of title
	 * 
	 * @return the value of title Get the value title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Get the value of userType
	 * 
	 * @return the value of userType Get the value userType
	 */
	public Integer getUserType() {
		return this.userType;
	}

	/**
	 * Get the value of language
	 * 
	 * @return the value of language Get the value language
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * Get the value of departementId
	 * 
	 * @return the value of departementId Get the value departementId
	 */
	public String getDepartementId() {
		return this.departementId;
	}

	/**
	 * Get the value of idGroup
	 * 
	 * @return the value of idGroup Get the value idGroup
	 */
	public String getIdGroup() {
		return this.idGroup;
	}

	/**
	 * Get the value of succursaleId
	 * 
	 * @return the value of succursaleId Get the value succursaleId
	 */
	public String getSuccursaleId() {
		return this.succursaleId;
	}

	/*
	 * public void setUserGroup(UserGroup userGroup) { this.userGroup =
	 * userGroup; }
	 *//**
	 * Set the value of userId
	 * 
	 * @param userId
	 *            * new value of userId
	 */
	public void setUserId(String userId) {
		if (this.compareFields(this.userId, userId)) {
			this.fireUpdatePendingChanged(true);
		}
		this.userId = userId != null ? userId.trim() : null;
	}

	/**
	 * Set the value of firstName
	 * 
	 * @param firstName
	 *            * new value of firstName
	 */
	public void setFirstName(String firstName) {
		if (this.compareFields(this.firstName, firstName)) {
			this.fireUpdatePendingChanged(true);
		}
		this.firstName = firstName != null ? firstName.trim() : null;
	}

	/**
	 * Set the value of lastName
	 * 
	 * @param lastName
	 *            * new value of lastName
	 */
	public void setLastName(String lastName) {
		if (this.compareFields(this.lastName, lastName)) {
			this.fireUpdatePendingChanged(true);
		}
		this.lastName = lastName != null ? lastName.trim() : null;
	}

	/**
	 * Set the value of sex
	 * 
	 * @param sex
	 *            * new value of sex
	 */
	public void setSex(Integer sex) {
		if (this.compareFields(this.sex, sex)) {
			this.fireUpdatePendingChanged(true);
		}
		this.sex = sex;
	}

	/**
	 * Set the value of title
	 * 
	 * @param title
	 *            * new value of title
	 */
	public void setTitle(String title) {
		if (this.compareFields(this.title, title)) {
			this.fireUpdatePendingChanged(true);
		}
		this.title = title != null ? title.trim() : null;
	}

	/**
	 * Set the value of userType
	 * 
	 * @param userType
	 *            * new value of userType
	 */
	public void setUserType(Integer userType) {
		if (this.compareFields(this.userType, userType)) {
			this.fireUpdatePendingChanged(true);
		}
		this.userType = userType;
	}

	/**
	 * Set the value of language
	 * 
	 * @param language
	 *            * new value of language
	 */
	public void setLanguage(String language) {
		if (this.compareFields(this.language, language)) {
			this.fireUpdatePendingChanged(true);
		}
		this.language = language != null ? language.trim() : null;
	}

	/**
	 * Set the value of departementId
	 * 
	 * @param departementId
	 *            * new value of departementId
	 */
	public void setDepartementId(String departementId) {
		if (this.compareFields(this.departementId, departementId)) {
			this.fireUpdatePendingChanged(true);
		}
		this.departementId = departementId != null ? departementId.trim()
				: null;
	}

	/**
	 * Set the value of idGroup
	 * 
	 * @param idGroup
	 *            * new value of idGroup
	 */
	public void setIdGroup(String idGroup) {
		if (this.compareFields(this.idGroup, idGroup)) {
			this.fireUpdatePendingChanged(true);
		}
		this.idGroup = idGroup != null ? idGroup.trim() : null;
	}

	/**
	 * Set the value of succursaleId
	 * 
	 * @param succursaleId
	 *            * new value of succursaleId
	 */
	public void setSuccursaleId(String succursaleId) {
		if (this.compareFields(this.succursaleId, succursaleId)) {
			this.fireUpdatePendingChanged(true);
		}
		this.succursaleId = succursaleId != null ? succursaleId.trim() : null;
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
		UserProfileOld other = (UserProfileOld) obj;
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

		if (userId != null) {
			builder.append(userId);
		}
		if (firstName != null) {
			builder.append(firstName);
		}
		if (lastName != null) {
			builder.append(lastName);
		}
		if (sex != null) {
			builder.append(sex);
		}
		if (title != null) {
			builder.append(title);
		}
		if (userType != null) {
			builder.append(userType);
		}
		if (language != null) {
			builder.append(language);
		}
		if (departementId != null) {
			builder.append(departementId);
		}
		if (idGroup != null) {
			builder.append(idGroup);
		}
		if (succursaleId != null) {
			builder.append(succursaleId);
		}
		return builder.toString();
	}

	public void merge(ModelBase modelBase) {
		UserProfileOld model = (UserProfileOld) modelBase;
		setEntityId(model.getEntityId());
		setErrorMessage(model.getErrorMessage());
		setCreatedBy(model.getCreatedBy());
		setCreatedOn(model.getCreatedOn());
		setUpdatedBy(model.getUpdatedBy());
		setUpdatedOn(model.getUpdatedOn());
		setRowscn(model.getRowscn());
		setRowscn(model.getRowscn());

		userId = model.getUserId();
		firstName = model.getFirstName();
		lastName = model.getLastName();
		sex = model.getSex();
		title = model.getTitle();
		userType = model.getUserType();
		language = model.getLanguage();
		departementId = model.getDepartementId();
		idGroup = model.getIdGroup();
		succursaleId = model.getSuccursaleId();
	}

	@Override
	public void validateModel() throws ModelException {
		StringBuilder builder = new StringBuilder();

		if (userId == null || userId.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("userId");
			} else {
				builder.append("|userId");
			}

		}
		if (firstName == null || firstName.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("firstName");
			} else {
				builder.append("|firstName");
			}

		}
		if (lastName == null || lastName.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("lastName");
			} else {
				builder.append("|lastName");
			}

		}
		if (title == null || title.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("title");
			} else {
				builder.append("|title");
			}

		}
		if (userType == null) {
			builder.append("userType");
		}
		if (language == null || language.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("language");
			} else {
				builder.append("|language");
			}

		}
		if (succursaleId == null || succursaleId.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("succursaleId");
			} else {
				builder.append("|succursaleId");
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
		return getUserId();
	}

	public static UserProfileOld parse(String value) {
		UserProfileOld model = new UserProfileOld();
		// Set PkValue to complete this class
		model.setUserId(value);
		return model;
	}

	@Override
	public Object getPrimaryKey() {
		return userId;
	}
}