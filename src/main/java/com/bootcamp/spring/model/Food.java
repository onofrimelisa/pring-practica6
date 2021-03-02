package com.bootcamp.spring.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Food {
    private String name;
    private List<Ingredient> ingredients;

    public Food(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }
}
