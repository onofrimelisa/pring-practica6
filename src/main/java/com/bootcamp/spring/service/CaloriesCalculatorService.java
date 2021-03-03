package com.bootcamp.spring.service;

import com.bootcamp.spring.interfaces.ICaloriesCalculatorRepository;
import com.bootcamp.spring.interfaces.ICaloriesCalculatorService;
import com.bootcamp.spring.dto.ResponseDTO;
import com.bootcamp.spring.model.Food;
import com.bootcamp.spring.model.Ingredient;
import com.bootcamp.spring.model.IngredientsWithCalories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaloriesCalculatorService implements ICaloriesCalculatorService {
    @Autowired
    private ICaloriesCalculatorRepository caloriesCalculatorRepository;

    @Override
    public ResponseDTO generateResponse(Food food) {
        ResponseDTO response = new ResponseDTO();
        response.setCaloriesPerIngredient(this.calculateCaloriesPerIngredient(food.getIngredients()));
        response.setTotalCalories(this.calculateTotalCalories(food));
        response.setIngredientWithMostCalories(this.calculateIngredientWithMostCalories(food.getIngredients()));
        return response;
    }

    @Override
    public Double calculateTotalCalories(Food food) {
        return this.caloriesCalculatorRepository.calculateTotalCalories(food);
    }

    @Override
    public List<IngredientsWithCalories> calculateCaloriesPerIngredient(List<Ingredient> ingredients) {
        return this.caloriesCalculatorRepository.calculateCaloriesPerIngredient(ingredients);
    }

    @Override
    public Ingredient calculateIngredientWithMostCalories(List<Ingredient> ingredients) {
        return this.caloriesCalculatorRepository.calculateIngredientWithMostCalories(ingredients);
    }
}
