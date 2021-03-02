package com.bootcamp.spring.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class FoodDTO {
    private String name;
    private List<IngredientDTO> ingredients;
}
