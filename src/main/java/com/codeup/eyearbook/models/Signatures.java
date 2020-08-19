package com.codeup.eyearbook.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "signatures")
public class Signatures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String yearbook_comment;

    @ManyToOne
    @JoinColumn(name = "signer_id")
    private User signer;

    @Column(columnDefinition = "boolean default false")
    private boolean is_flagged;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp created_at;

    public Signatures(){}

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private User profile_user;

    public Signatures(long id, String yearbook_comment, User signer, boolean is_flagged, Timestamp created_at, User profile_user) {
        this.id = id;
        this.yearbook_comment = yearbook_comment;
        this.signer = signer;
        this.is_flagged = is_flagged;
        this.created_at = created_at;
        this.profile_user = profile_user;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getYearbook_comment() {
        return yearbook_comment;
    }

    public void setYearbook_comment(String yearbook_comment) {
        this.yearbook_comment = yearbook_comment;
    }

    public User getSigner() {
        return signer;
    }

    public void setSigner(User signer) {
        this.signer = signer;
    }

    public boolean isIs_flagged() {
        return is_flagged;
    }

    public void setIs_flagged(boolean is_flagged) {
        this.is_flagged = is_flagged;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public User getProfile_user() {
        return profile_user;
    }

    public void setProfile_user(User profile_user) {
        this.profile_user = profile_user;
    }

    public void save(Signatures signatures) {
    }
}
