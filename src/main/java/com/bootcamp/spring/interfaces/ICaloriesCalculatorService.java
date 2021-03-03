package com.bootcamp.spring.interfaces;

import com.bootcamp.spring.dto.ResponseDTO;
import com.bootcamp.spring.model.Food;
import com.bootcamp.spring.model.Ingredient;
import com.bootcamp.spring.model.IngredientsWithCalories;

import java.util.List;

public interface ICaloriesCalculatorService {
    ResponseDTO generateResponse(Food food);
    Double calculateTotalCalories(Food food);
    List<IngredientsWithCalories> calculateCaloriesPerIngredient(List<Ingredient> ingredients);
    Ingredient calculateIngredientWithMostCalories(List<Ingredient> ingredients);
}
