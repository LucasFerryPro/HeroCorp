package com.lulu.HeroCorp.model;

import jakarta.persistence.*;

@Entity
@Table(name="categories")
public class ItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public ItemCategory(){}

    public ItemCategory(long id, String name){
        this.id = id;
        this.name = name;
    }

    public ItemCategory(String name){
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        return "Category{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
