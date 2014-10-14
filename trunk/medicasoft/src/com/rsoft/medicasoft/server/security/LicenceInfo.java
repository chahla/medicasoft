/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.medicasoft.server.security;

/**
 *
 * @author Robelkend
 */
public class LicenceInfo {

    private String companyName;
    private String contactName;
    private String email;
    private String phoneNumbers;
    private Integer validity;
    private Integer nbLicence;
    private String licenceType;
    private String countryCode;
    private String companyAddress;
    private String keyPassword;
    private String certPassword;

    public LicenceInfo(String companyName, String contactName, String email, String phoneNumbers, Integer validity, Integer nbLicence, String licenceType, String countryCode, String companyAddress, String keyPassword, String certPassword) {
        this.companyName = companyName;
        this.contactName = contactName;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
        this.validity = validity;
        this.nbLicence = nbLicence;
        this.licenceType = licenceType;
        this.countryCode = countryCode;
        this.companyAddress = companyAddress;
        this.keyPassword = keyPassword;
        this.certPassword = certPassword;
    }

    public LicenceInfo(String companyName, String contactName, String email, String phoneNumbers, Integer validity, Integer nbLicence, String licenceType, String companyAddress, String keyPassword, String certPassword) {
        this.companyName = companyName;
        this.contactName = contactName;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
        this.validity = validity;
        this.nbLicence = nbLicence;
        this.licenceType = licenceType;
        this.companyAddress = companyAddress;
        this.keyPassword = keyPassword;
        this.certPassword = certPassword;
    }

    public LicenceInfo(String companyName, String contactName, String email, String phoneNumbers, Integer validity, Integer nbLicence, String licenceType, String companyAddress) {
        this.companyName = companyName;
        this.contactName = contactName;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
        this.validity = validity;
        this.nbLicence = nbLicence;
        this.licenceType = licenceType;
        this.companyAddress = companyAddress;
    }

    public LicenceInfo() {
    }

    /**
     * Get the value of countryCode
     *
     * @return the value of countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Set the value of countryCode
     *
     * @param countryCode new value of countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Get the value of certPassword
     *
     * @return the value of certPassword
     */
    public String getCertPassword() {
        return certPassword;
    }

    /**
     * Set the value of certPassword
     *
     * @param certPassword new value of certPassword
     */
    public void setCertPassword(String certPassword) {
        this.certPassword = certPassword;
    }

    /**
     * Get the value of keyPassword
     *
     * @return the value of keyPassword
     */
    public String getKeyPassword() {
        return keyPassword;
    }

    /**
     * Set the value of keyPassword
     *
     * @param keyPassword new value of keyPassword
     */
    public void setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
    }

    /**
     * Get the value of companyAddress
     *
     * @return the value of companyAddress
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * Set the value of companyAddress
     *
     * @param companyAddress new value of companyAddress
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    /**
     * Get the value of licenceType
     *
     * @return the value of licenceType
     */
    public String getLicenceType() {
        return licenceType;
    }

    /**
     * Set the value of licenceType
     *
     * @param licenceType new value of licenceType
     */
    public void setLicenceType(String licenceType) {
        this.licenceType = licenceType;
    }

    /**
     * Get the value of nbLicence
     *
     * @return the value of nbLicence
     */
    public Integer getNbLicence() {
        return nbLicence;
    }

    /**
     * Set the value of nbLicence
     *
     * @param nbLicence new value of nbLicence
     */
    public void setNbLicence(Integer nbLicence) {
        this.nbLicence = nbLicence;
    }

    /**
     * Get the value of validity
     *
     * @return the value of validity
     */
    public int getValidity() {
        return validity;
    }

    /**
     * Set the value of validity
     *
     * @param validity new value of validity
     */
    public void setValidity(int validity) {
        this.validity = validity;
    }

    /**
     * Get the value of phoneNumbers
     *
     * @return the value of phoneNumbers
     */
    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     * Set the value of phoneNumbers
     *
     * @param phoneNumbers new value of phoneNumbers
     */
    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of contactName
     *
     * @return the value of contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Set the value of contactName
     *
     * @param contactName new value of contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Get the value of companyName
     *
     * @return the value of companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Set the value of companyName
     *
     * @param companyName new value of companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
