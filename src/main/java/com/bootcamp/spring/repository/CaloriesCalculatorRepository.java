package com.bootcamp.spring.repository;

import com.bootcamp.spring.interfaces.ICaloriesCalculatorRepository;
import com.bootcamp.spring.model.Food;
import com.bootcamp.spring.model.Ingredient;
import com.bootcamp.spring.model.IngredientsWithCalories;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Repository
public class CaloriesCalculatorRepository implements ICaloriesCalculatorRepository {
    private List<IngredientsWithCalories> ingredientsWithCaloriesList;

    public CaloriesCalculatorRepository() {
        this.ingredientsWithCaloriesList = this.loadDatabase();
    }

    public List<IngredientsWithCalories> loadDatabase() {
        File file = null;

        try{
            file = ResourceUtils.getFile("classpath:calories.json");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<IngredientsWithCalories>> typeRef = new TypeReference<>() {};
        List<IngredientsWithCalories> ingredientsWithCaloriesList = null;

        try{
            ingredientsWithCaloriesList = objectMapper.readValue(file, typeRef);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return ingredientsWithCaloriesList;
    }

    @Override
    public IngredientsWithCalories calculateIngredientWithMostCalories(List<IngredientsWithCalories> caloriesPerIngredient) {
        return Collections.max(caloriesPerIngredient, (ingredient1, ingredient2) -> ingredient1.getCalories().compareTo(ingredient2.getCalories()));

    }

    @Override
    public Optional<IngredientsWithCalories> searchIngredientByName(String name) {
        return this.ingredientsWithCaloriesList
            .stream()
            .filter(ingredientWithCaloriesDTO -> ingredientWithCaloriesDTO.getName().toUpperCase(Locale.ROOT).equals(name.toUpperCase()))
            .findFirst();
    }
}
