package com.bootcamp.spring.exceptions;

public class IngredientNotFoundException extends Exception{
    public IngredientNotFoundException(String message) {
        super(message);
    }
}
