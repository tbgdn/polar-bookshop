package com.tbgdn.polar.catalog.controller;

import com.google.common.base.Strings;
import com.tbgdn.polar.catalog.domain.BookAlreadyExistsException;
import com.tbgdn.polar.catalog.domain.BookNotFoundException;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookControllerAdvice {

  @ExceptionHandler(BookNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String bookNotFound(BookNotFoundException ex) {
    return ex.getMessage();
  }

  @ExceptionHandler(BookAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  String bookAlreadyExists(BookAlreadyExistsException ex) {
    return ex.getMessage();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    return ex.getBindingResult().getFieldErrors()
        .stream()
        .collect(Collectors.toMap(FieldError::getField, error -> Strings.nullToEmpty(error.getDefaultMessage())));
  }

}
