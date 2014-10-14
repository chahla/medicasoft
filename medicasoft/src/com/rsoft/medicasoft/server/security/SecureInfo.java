/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.medicasoft.server.security;

import java.util.Date;

/**
 *
 * @author Robelkend
 */
public class SecureInfo {

    private String fingerprint;
    private Date creationDate;

    public SecureInfo(String fingerprint, Date creationDate) {
        this.fingerprint = fingerprint;
        this.creationDate = creationDate;
    }

    public SecureInfo() {
    }

    /**
     * Get the value of creationDate
     *
     * @return the value of creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Set the value of creationDate
     *
     * @param creationDate new value of creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Get the value of fingerprint
     *
     * @return the value of fingerprint
     */
    public String getFingerprint() {
        return fingerprint;
    }

    /**
     * Set the value of fingerprint
     *
     * @param fingerprint new value of fingerprint
     */
    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }
}
