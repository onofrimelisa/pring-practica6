package com.bootcamp.spring.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IngredientsWithCalories {
    private String name;
    private Double calories;

    public IngredientsWithCalories() {
    }

    public IngredientsWithCalories(String name, Double calories) {
        this.name = name;
        this.calories = calories;
    }
}
