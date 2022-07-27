package com.stepanwxw.crudv2.model;

import java.sql.Timestamp;

public class Post {
   private long id;
   private String content;
   private Timestamp create;
   private Timestamp update;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreate() {
        return create;
    }

    public void setCreate(Timestamp create) {
        this.create = create;
    }

    public Timestamp getUpdate() {
        return update;
    }

    public void setUpdate(Timestamp update) {
        this.update = update;
    }
    public Post(long id, String content, Timestamp create, Timestamp update) {
        this.id = id;
        this.content = content;
        this.create = create;
        this.update = update;
    }

    public Post(long id, String content, Timestamp update) {
        this.id = id;
        this.content = content;
        this.update = update;
    }

    public Post(String content) {
        this.content = content;
    }

    public Post(long id) {
        this.id = id;
    }

    public Post(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", create=" + create +
                ", update=" + update +
                '}';
    }
}
