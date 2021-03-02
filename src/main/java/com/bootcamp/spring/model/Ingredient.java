package com.bootcamp.spring.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Ingredient {
    private String name;
    private Double weight;

    public Ingredient(String name, Double weight) {
        this.name = name;
        this.weight = weight;
    }
}
