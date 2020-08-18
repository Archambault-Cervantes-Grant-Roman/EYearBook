package com.codeup.eyearbook.models;

import javax.persistence.*;

@Entity
@Table(name = "yearbook_pages")
public class Yearbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 150)
    private String img_path;

    @Column(length = 150)
    private int page_num;

    public Yearbook(long id, String img_path, int page_num) {
        this.id = id;
        this.img_path = img_path;
        this.page_num = page_num;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }
}
