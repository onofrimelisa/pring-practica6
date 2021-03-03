package com.bootcamp.spring.interfaces;

import com.bootcamp.spring.model.Ingredient;
import com.bootcamp.spring.model.IngredientsWithCalories;
import java.util.List;
import java.util.Optional;

public interface ICaloriesCalculatorRepository {
    List<IngredientsWithCalories> loadDatabase();
    IngredientsWithCalories calculateIngredientWithMostCalories(List<IngredientsWithCalories> caloriesPerIngredient);
    Optional<IngredientsWithCalories> searchIngredientByName(String name);
}
