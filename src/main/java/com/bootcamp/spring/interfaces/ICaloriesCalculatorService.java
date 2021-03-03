package com.bootcamp.spring.interfaces;

import com.bootcamp.spring.dto.ResponseDTO;
import com.bootcamp.spring.exceptions.IngredientNotFoundException;
import com.bootcamp.spring.model.Food;
import com.bootcamp.spring.model.Ingredient;
import com.bootcamp.spring.model.IngredientsWithCalories;

import java.util.List;

public interface ICaloriesCalculatorService {
    ResponseDTO generateResponse(Food food) throws IngredientNotFoundException;
    Double calculateTotalCalories(Food food) throws IngredientNotFoundException;
    List<IngredientsWithCalories> calculateCaloriesPerIngredient(List<Ingredient> ingredients) throws IngredientNotFoundException;
    Ingredient calculateIngredientWithMostCalories(List<Ingredient> ingredients) throws IngredientNotFoundException;
}
