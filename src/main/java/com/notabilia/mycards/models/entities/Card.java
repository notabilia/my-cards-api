package com.notabilia.mycards.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Card implements EntityModel<Integer> {

    @JsonIgnore
    private static final Double MODEL_VERSION = 1.0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String description;
    private LocalDate purchaseDate;
    private String merchant;
    private Double price;

    // For JSON deserialisation
    public Card() {}

    public Card(Integer id) {
        this(id, null, null, null, null);
    }

    public Card(
            Integer id,
            String description,
            LocalDate purchaseDate,
            String merchant,
            Double price
    ) {
        this.id = id;
        this.description = description;
        this.purchaseDate = purchaseDate;
        this.merchant = merchant;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descrption) {
        this.description = descrption;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public Double getModelVersion() {
        return MODEL_VERSION;
    }
}
