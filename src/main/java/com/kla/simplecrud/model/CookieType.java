package com.kla.simplecrud.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Maps to all the different types of cookies out there. i.e. "Chocolate Chip" and "Snickerdoodle".
 */
@Entity
@Table (name = "cookie_type")
public class CookieType {

    @Id @Column(name = "type", length = 4)
    private String type;

    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "userid", length = 6)
    private String userId;

    @Temporal(TemporalType.DATE)
    @Column(name = "activity")
    private Date activity;

    public CookieType() {

    }

    public CookieType(String type) {
        this.type = type;
        this.userId = "Shawn";
    }

    public CookieType(String type, String description) {
        this.type = type;
        this.description = description;
        this.userId = "Shawn";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userid) {
        this.userId = userid;
    }

    public Date getActivity() {
        return activity;
    }

    public void setActivity(Date activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    @PrePersist
    protected void onCreate() {
        this.activity = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.activity = new Date();
    }
}
