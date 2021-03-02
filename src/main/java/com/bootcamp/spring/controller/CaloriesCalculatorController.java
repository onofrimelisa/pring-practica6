package com.bootcamp.spring.controller;

import com.bootcamp.spring.interfaces.ICaloriesCalculatorService;
import com.bootcamp.spring.dto.FoodDTO;
import com.bootcamp.spring.dto.IngredientDTO;
import com.bootcamp.spring.dto.ResponseDTO;
import com.bootcamp.spring.model.Food;
import com.bootcamp.spring.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaloriesCalculatorController {
    @Autowired
    private ICaloriesCalculatorService caloriesCalculatorService;

    @PostMapping
    public ResponseDTO calculateCalories(@RequestBody FoodDTO foodDTO) {
        Food food = new Food(foodDTO.getName());

        for (IngredientDTO ingredientDTO:
                foodDTO.getIngredients()) {
            Ingredient ingredient = new Ingredient(ingredientDTO.getName(), ingredientDTO.getWeight());
            food.addIngredient(ingredient);
        }

        return caloriesCalculatorService.generateResponse(food);
    }
}
