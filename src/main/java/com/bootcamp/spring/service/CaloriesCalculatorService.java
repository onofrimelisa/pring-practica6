package com.bootcamp.spring.service;

import com.bootcamp.spring.exceptions.IngredientNotFoundException;
import com.bootcamp.spring.interfaces.ICaloriesCalculatorRepository;
import com.bootcamp.spring.interfaces.ICaloriesCalculatorService;
import com.bootcamp.spring.dto.ResponseDTO;
import com.bootcamp.spring.model.Food;
import com.bootcamp.spring.model.Ingredient;
import com.bootcamp.spring.model.IngredientsWithCalories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        List<IngredientsWithCalories> caloriesPerIngredient = this.calculateCaloriesPerIngredient(food.getIngredients());

        return caloriesPerIngredient
                .stream()
                .mapToDouble(IngredientsWithCalories::getCalories)
                .reduce(Double::sum)
                .orElse(0);
    }

    @Override
    public List<IngredientsWithCalories> calculateCaloriesPerIngredient(List<Ingredient> ingredients) {
        List<IngredientsWithCalories> list = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            Optional<IngredientsWithCalories> ingredientCalories = this.caloriesCalculatorRepository.searchIngredientByName(ingredient.getName());
            if (ingredientCalories.isPresent()) {
                IngredientsWithCalories newIngredient = new IngredientsWithCalories(
                        ingredient.getName(),
                        ((ingredientCalories.get().getCalories() * ingredient.getWeight()) / 100)
                );
                list.add(newIngredient);
            }else{
                throw new IngredientNotFoundException(ingredient.getName());
            }
        }

        return list;
    }

    @Override
    public Ingredient calculateIngredientWithMostCalories(List<Ingredient> ingredients) {
        List<IngredientsWithCalories> caloriesPerIngredient = this.calculateCaloriesPerIngredient(ingredients);
        IngredientsWithCalories ingredientWithCaloriesMax = this.caloriesCalculatorRepository.calculateIngredientWithMostCalories(caloriesPerIngredient);

        return ingredients
            .stream()
            .filter(ingredient -> ingredient.getName().toUpperCase(Locale.ROOT).equals(ingredientWithCaloriesMax.getName().toUpperCase()))
            .findFirst()
            .get();

    }
}
