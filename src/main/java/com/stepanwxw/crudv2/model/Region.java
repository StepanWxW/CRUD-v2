package com.stepanwxw.crudv2.model;

public class Region {
    private long id;
    private String name;


    public Region(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Region(String name) {
        this.name = name;
    }

    public Region(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}