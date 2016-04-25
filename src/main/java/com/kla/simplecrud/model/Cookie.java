package com.kla.simplecrud.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Represents an individual cookie and the recipe for making that cookie.
 */
@Entity
@Table(name = "cookies")
public class Cookie {

    @Id @SequenceGenerator(name="cookie_seq",sequenceName="cookie_id_counter", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cookie_seq")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "recipe", length = 1000)
    private String recipe;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.MERGE, targetEntity = CookieType.class)
    @JoinTable (name = "cookie_type_rel", joinColumns = {
                @JoinColumn (name = "id", nullable = false, updatable = false)
            }, inverseJoinColumns = {
                @JoinColumn(name = "type", nullable = false, updatable = false)
            })
    @OrderBy("description")
    private Set<CookieType> cookieTypes;

    @Column(name = "userid", length = 6)
    private String userId;

    @Temporal(TemporalType.DATE)
    @Column(name = "activity")
    private Date activity;

    public Cookie() {}

    public Cookie(long id, String recipe, Set<CookieType> cookieTypes) {
        this.id = id;
        this.recipe = recipe;
        this.cookieTypes = cookieTypes;
        this.userId = "Shawn";
    }

    public Cookie(String recipe, Set<CookieType> cookieTypes) {
        this.recipe = recipe;
        this.cookieTypes = cookieTypes;
        this.userId = "Shawn";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public Date getActivity() {
        return activity;
    }

    public void setActivity(Date activity) {
        this.activity = activity;
    }

    public Set<CookieType> getCookieTypes() {
        return cookieTypes;
    }

    public void setCookieTypes(Set<CookieType> cookieTypes) {
        this.cookieTypes = cookieTypes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
