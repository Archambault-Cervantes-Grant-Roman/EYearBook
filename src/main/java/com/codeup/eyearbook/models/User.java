package com.codeup.eyearbook.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length=20, nullable=true)
    private long parent_id;

    @Column(length = 100, nullable = true, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(columnDefinition = "boolean default true")
    private boolean is_parent;

    @Column(columnDefinition = "boolean default false")
    private boolean owns_yearbook;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(length = 150)
    private String sign_page_banner_image;

    @Column(length = 20, nullable = false, unique = true)
    private String username;

    @Transient
    private String retypePassword;





    public User() {
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
        is_parent = copy.is_parent;
        owns_yearbook = copy.owns_yearbook;
        parent_id = copy.parent_id;
        sign_page_banner_image = copy.sign_page_banner_image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_parent() {
        return is_parent;
    }

    public void setIs_parent(boolean is_parent) {
        this.is_parent = is_parent;
    }

    public boolean isOwns_yearbook() {
        return owns_yearbook;
    }

    public void setOwns_yearbook(boolean owns_yearbook) {
        this.owns_yearbook = owns_yearbook;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSign_page_banner_image() {
        return sign_page_banner_image;
    }

    public void setSign_page_banner_image(String sign_page_banner_image) {
        this.sign_page_banner_image = sign_page_banner_image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}