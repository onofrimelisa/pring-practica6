package com.bootcamp.spring.interfaces;

import com.bootcamp.spring.model.Food;
import com.bootcamp.spring.model.Ingredient;
import com.bootcamp.spring.model.IngredientsWithCalories;
import java.util.List;

public interface ICaloriesCalculatorRepository {
    List<IngredientsWithCalories> loadDatabase();
    Double calculateTotalCalories(Food food);
    List<IngredientsWithCalories> calculateCaloriesPerIngredient(List<Ingredient> ingredients);
    Ingredient calculateIngredientWithMostCalories(List<Ingredient> ingredients);
}
