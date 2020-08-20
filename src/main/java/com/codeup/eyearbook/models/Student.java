package com.codeup.eyearbook.models;

import com.codeup.eyearbook.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;



@Entity
@Table(name = "student_records")
@Service
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 150)
    private long student_id;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "student")
    private User user;



    @Column(length = 150)
    private String image;

    @Column(length = 150)
    private String first_name;

    @Column(length = 150)
    private String last_name;

    public Student(){}



    public Student(long id, long student_id, String image, String first_name, String last_name, User user) {
        this.id = id;
        this.image = image;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user = user;
        this.student_id = student_id;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
