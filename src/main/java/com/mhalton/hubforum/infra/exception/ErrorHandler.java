package com.mhalton.hubforum.infra.exception;

import com.mhalton.hubforum.domain.CustValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler
{
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundException> handle404Error()
//    public ResponseEntity<String> handle404Error(EntityNotFoundException ex)
    {
        return ResponseEntity.notFound().build();
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorData>> handle400Error(MethodArgumentNotValidException ex)
    {
        List<FieldError> errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream()
                                                      .map(ValidationErrorData::new).toList());
    }

    @ExceptionHandler(CustValidationException.class)
    public ResponseEntity<String> handleValidationError(CustValidationException ex)
    {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    public record ValidationErrorData(String field, String message)
    {
        public ValidationErrorData(FieldError error)
        {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
