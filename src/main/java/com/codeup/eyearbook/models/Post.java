package com.codeup.eyearbook.models;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    public Post(){}

    public Post(long id, String title, String body){
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body, User author) {
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public void setBody(String newBody){
        this.body = newBody;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
