package com.notabilia.mycards.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card implements EntityModel<Integer> {

    @JsonIgnore
    private static final Double MODEL_VERSION = 1.0;

    // For JSON deserialisation
    public Card() {}

    public Card(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public Double getModelVersion() {
        return MODEL_VERSION;
    }
}
