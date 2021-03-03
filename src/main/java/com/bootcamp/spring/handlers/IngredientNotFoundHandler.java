package com.bootcamp.spring.handlers;

import com.bootcamp.spring.dto.ErrorDTO;
import com.bootcamp.spring.exceptions.IngredientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IngredientNotFoundHandler {

    @ExceptionHandler(IngredientNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleException(IngredientNotFoundException exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("The ingredient " + exception.getMessage() + " is not valid.");
        errorDTO.setName("Invalid ingredient");
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
