package com.rsoft.medicasoft.shared.model;

/*
 Templates Generator
 */

/*@Author=Jean Louidort*/
/*@Generation Date=Mon Oct 07 22:53:50 EDT 2013*/
/*@Version=1.0*/
import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.IgnoreSave;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.OnLoad;
import com.googlecode.objectify.annotation.OnSave;
import com.googlecode.objectify.condition.IfNotNull;
import com.rsoft.medicasoft.shared.ModelBase;
import com.rsoft.medicasoft.shared.ModelException;
import com.rsoft.medicasoft.shared.PersistenceException;

@Entity
public class UserProfile extends ModelBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Index({ IfNotNull.class })
	@Load
	private Ref<UserGroup> userGroupId;
	@IgnoreSave
	private UserGroup userGroup;
	@Index({ IfNotNull.class })
	@Load
	private Ref<Institution> institutionId;
	@IgnoreSave
	private Institution institution;

	@Index({ IfNotNull.class })
	private String userId;

	private String title;

	private String firstName;

	private String lastName;

	@Index({ IfNotNull.class })
	private String status;

	@Index({ IfNotNull.class })
	private String sex;

	private String language;

	private String pinCode;

	private Date expirationDate;

	@Index({ IfNotNull.class })
	private String email;

	public UserProfile() {
	}

	/**
	 * Get the value of userGroupId
	 * 
	 * @return the value of userGroupId Get the value userGroupId
	 */
	public Ref<UserGroup> getUserGroupId() {
		return this.userGroupId;
	}

	/**
	 * Get the value of userGroup
	 * 
	 * @return the value of userGroup Get the value userGroup
	 */
	public UserGroup getUserGroup() {
		return this.userGroup;
	}

	/**
	 * Get the value of institutionId
	 * 
	 * @return the value of institutionId Get the value institutionId
	 */
	public Ref<Institution> getInstitutionId() {
		return this.institutionId;
	}

	/**
	 * Get the value of institution
	 * 
	 * @return the value of institution Get the value institution
	 */
	public Institution getInstitution() {
		return this.institution;
	}

	/**
	 * Get the value of userId
	 * 
	 * @return the value of userId Get the value userId
	 */
	public String getUserId() {
		return this.userId;
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
	 * Get the value of status
	 * 
	 * @return the value of status Get the value status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Get the value of sex
	 * 
	 * @return the value of sex Get the value sex
	 */
	public String getSex() {
		return this.sex;
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
	 * Get the value of pinCode
	 * 
	 * @return the value of pinCode Get the value pinCode
	 */
	public String getPinCode() {
		return this.pinCode;
	}

	/**
	 * Get the value of expirationDate
	 * 
	 * @return the value of expirationDate Get the value expirationDate
	 */
	public Date getExpirationDate() {
		return this.expirationDate;
	}

	/**
	 * Get the value of email
	 * 
	 * @return the value of email Get the value email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Set the value of userGroupId
	 * 
	 * @param userGroupId
	 *            * new value of userGroupId
	 */
	public void setUserGroupId(Ref<UserGroup> userGroupId) {
		this.userGroupId = userGroupId;
	}

	/**
	 * Set the value of userGroup
	 * 
	 * @param userGroup
	 *            * new value of userGroup
	 */
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	/**
	 * Set the value of institutionId
	 * 
	 * @param institutionId
	 *            * new value of institutionId
	 */
	public void setInstitutionId(Ref<Institution> institutionId) {
		this.institutionId = institutionId;
	}

	/**
	 * Set the value of institution
	 * 
	 * @param institution
	 *            * new value of institution
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	/**
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
	 * Set the value of status
	 * 
	 * @param status
	 *            * new value of status
	 */
	public void setStatus(String status) {
		if (this.compareFields(this.status, status)) {
			this.fireUpdatePendingChanged(true);
		}
		this.status = status != null ? status.trim() : null;
	}

	/**
	 * Set the value of sex
	 * 
	 * @param sex
	 *            * new value of sex
	 */
	public void setSex(String sex) {
		if (this.compareFields(this.sex, sex)) {
			this.fireUpdatePendingChanged(true);
		}
		this.sex = sex != null ? sex.trim() : null;
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
	 * Set the value of pinCode
	 * 
	 * @param pinCode
	 *            * new value of pinCode
	 */
	public void setPinCode(String pinCode) {
		if (this.compareFields(this.pinCode, pinCode)) {
			this.fireUpdatePendingChanged(true);
		}
		this.pinCode = pinCode != null ? pinCode.trim() : null;
	}

	/**
	 * Set the value of expirationDate
	 * 
	 * @param expirationDate
	 *            * new value of expirationDate
	 */
	public void setExpirationDate(Date expirationDate) {
		if (this.compareFields(this.expirationDate, expirationDate)) {
			this.fireUpdatePendingChanged(true);
		}
		this.expirationDate = expirationDate;
	}

	/**
	 * Set the value of email
	 * 
	 * @param email
	 *            * new value of email
	 */
	public void setEmail(String email) {
		if (this.compareFields(this.email, email)) {
			this.fireUpdatePendingChanged(true);
		}
		this.email = email != null ? email.trim() : null;
	}

	@OnSave
	public void beforeSave() throws PersistenceException {
		if (userGroup != null) {
			userGroupId = Ref.create(userGroup);
		}
		if (institution != null) {
			institutionId = Ref.create(institution);
		}
	}

	@OnLoad
	public void afterLoad() throws PersistenceException {
		if (userGroupId != null) {
			userGroup = userGroupId.getValue();
		}
		if (institutionId != null) {
			institution = institutionId.getValue();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode(2 0 )
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
		UserProfile other = (UserProfile) obj;
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
		UserProfile model = (UserProfile) modelBase;
		setEntityId(model.getEntityId());
		setErrorMessage(model.getErrorMessage());
		setCreatedBy(model.getCreatedBy());
		setCreatedOn(model.getCreatedOn());
		setUpdatedBy(model.getUpdatedBy());
		setUpdatedOn(model.getUpdatedOn());
		setRowscn(model.getRowscn());
		setRowscn(model.getRowscn());

		userId = model.getUserId();
		title = model.getTitle();
		firstName = model.getFirstName();
		lastName = model.getLastName();
		status = model.getStatus();
		sex = model.getSex();
		language = model.getLanguage();
		institutionId = model.getInstitutionId();
		userGroupId = model.getUserGroupId();
		pinCode = model.getPinCode();
		expirationDate = model.getExpirationDate();
		email = model.getEmail();
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
		if (title == null || title.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("title");
			} else {
				builder.append("|title");
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
		if (status == null || status.trim().isEmpty()) {
			status = "ACTIF";
		}
		if (sex == null || sex.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("sex");
			} else {
				builder.append("|sex");
			}

		}
		if (language == null || language.trim().isEmpty()) {
			if (builder.toString().isEmpty()) {
				builder.append("language");
			} else {
				builder.append("|language");
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
		if (this.getLineNo() != null) {
			return Long.toString(this.getLineNo());
		}
		return super.toString();
	}

	@Override
	public Object getPrimaryKey() {
		return this.getEntityId();
	}

	public static UserProfile parse(String value) {
		UserProfile model = new UserProfile();
		return model;
	}
}