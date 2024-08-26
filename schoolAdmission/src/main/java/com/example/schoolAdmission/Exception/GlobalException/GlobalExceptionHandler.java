package com.example.schoolAdmission.Exception.GlobalException;

import com.example.schoolAdmission.Exception.BoyNotFoundException;
import com.example.schoolAdmission.Exception.GirlNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({BoyNotFoundException.class, GirlNotFoundException.class})
    public Map<String,String> studentNotFound (RuntimeException ex){
           Map<String,String> error=new HashMap<>();
           error.put("error message is",ex.getMessage());
    return error;
}


@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String,String> studentExceptionHandler(MethodArgumentNotValidException ex){
       Map<String,String> errors=new HashMap<>();
       ex.getBindingResult()
        .getFieldErrors()
        .forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
return errors;
}
}

