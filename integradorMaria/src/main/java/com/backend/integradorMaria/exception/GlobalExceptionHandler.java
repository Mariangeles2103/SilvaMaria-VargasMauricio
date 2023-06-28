package com.backend.integradorMaria.exception;

import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarBadRequest(BadRequestException exception) {
        Map<String, String> exceptionMessage = new HashMap<>();
        exceptionMessage.put("path", String.valueOf(exception.getClass()));
        exceptionMessage.put("message", "Recurso no encontrado: " + exception.getMessage());
        return exceptionMessage;
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> procesarNotFoundException(ResourceNotFoundException exception){
        Map<String, String> exceptionMessage = new HashMap<>();
        exceptionMessage.put("message", "Recurso no encontrado: " + exception.getMessage());
        return exceptionMessage;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarValidationException(MethodArgumentNotValidException exception){
        Map<String, String> exceptionMessage = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            exceptionMessage.put(fieldName, errorMessage);
        });
        return exceptionMessage;
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put(exception.getPropertyName(), "El tipo de dato enviado no es igual al requerido");
        return mensaje;
    }
}
