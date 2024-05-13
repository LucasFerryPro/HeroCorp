package com.lulu.HeroCorp.model;


import jakarta.persistence.*;

@Entity
@Table(name="rpgitems")
public class RpgItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private long idCategory;

    private double price;

    private String effect;

    public RpgItem(){}

    public RpgItem(long id, String name, long idCategory, double price, String effect) {
        this.id = id;
        this.name = name;
        this.idCategory = idCategory;
        this.price = price;
        this.effect = effect;
    }

    public RpgItem(String name, long idCategory, double price, String effect) {
        this.name = name;
        this.idCategory = idCategory;
        this.price = price;
        this.effect = effect;
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

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }


}
