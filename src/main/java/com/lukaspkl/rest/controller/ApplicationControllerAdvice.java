package com.lukaspkl.rest.controller;

import com.lukaspkl.exception.RegraNegocioException;
import com.lukaspkl.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerRegranegocioException(RegraNegocioException ex){

        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }


}
