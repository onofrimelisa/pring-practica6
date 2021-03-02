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
            Optional<IngredientsWithCalories> ingredientCalories = searchIngredientByName(ingredient.getName());
            if (ingredientCalories.isPresent()) {
                IngredientsWithCalories newIngredient = new IngredientsWithCalories(
                        ingredient.getName(),
                        ((ingredientCalories.get().getCalories() * ingredient.getWeight()) / 100)
                );
                list.add(newIngredient);
            }
        }

        return list;
    }

    @Override
    public Ingredient calculateIngredientWithMostCalories(List<Ingredient> ingredients) {
        List<IngredientsWithCalories> caloriesPerIngredient = this.calculateCaloriesPerIngredient(ingredients);
        IngredientsWithCalories ingredientWithCaloriesMax = Collections.max(caloriesPerIngredient, (ingredient1, ingredient2) -> ingredient1.getCalories().compareTo(ingredient2.getCalories()));

        return ingredients
            .stream()
            .filter(ingredient -> ingredient.getName().toUpperCase(Locale.ROOT).equals(ingredientWithCaloriesMax.getName().toUpperCase()))
            .findFirst()
            .get();
    }

    private Optional<IngredientsWithCalories> searchIngredientByName(String name) {
        return this.ingredientsWithCaloriesList
            .stream()
            .filter(ingredientWithCaloriesDTO -> ingredientWithCaloriesDTO.getName().toUpperCase(Locale.ROOT).equals(name.toUpperCase()))
            .findFirst();
    }
}
