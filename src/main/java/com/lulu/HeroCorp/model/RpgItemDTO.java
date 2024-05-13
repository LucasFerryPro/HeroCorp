package com.lulu.HeroCorp.model;

public class RpgItemDTO {

    private String name;

    private String category;

    private Double price;

    private String effect;

    public RpgItemDTO(String name, String category, Double price, String effect) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.effect = effect;
    }

    public boolean isCreationValid(){
        return this.name != null && this.category != null && this.price != null;
    }

    public boolean isModifyValid(){
        return this.name != null || this.category != null || this.price != null || this.effect!=null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
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
