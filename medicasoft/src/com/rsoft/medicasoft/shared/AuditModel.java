/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.medicasoft.shared;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Index;

/**
 *
 * @author Jean Louidort
 */
public class AuditModel  implements Serializable {
	private static final long serialVersionUID = 1L;
    @Index
	private String createdBy;
    @Index
    private Date createdOn;
    @Index
    private String updatedBy;
    @Index
    private Date updatedOn;

    /**
     * Get the value of updatedOn
     *
     * @return the value of updatedOn
     */
    public Date getUpdatedOn() {
        return updatedOn;
    }

    /**
     * Set the value of updatedOn
     *
     * @param updatedOn new value of updatedOn
     */
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    /**
     * Get the value of updatedBy
     *
     * @return the value of updatedBy
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Set the value of updatedBy
     *
     * @param updatedBy new value of updatedBy
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * Get the value of createdOn
     *
     * @return the value of createdOn
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * Set the value of createdOn
     *
     * @param createdOn new value of createdOn
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Get the value of createdBy
     *
     * @return the value of createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the value of createdBy
     *
     * @param createdBy new value of createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
