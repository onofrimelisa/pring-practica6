package com.bootcamp.spring.dto;

import com.bootcamp.spring.model.Ingredient;
import com.bootcamp.spring.model.IngredientsWithCalories;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResponseDTO {
    private Double totalCalories;
    private List<IngredientsWithCalories> caloriesPerIngredient;
    private Ingredient ingredientWithMostCalories;
}
