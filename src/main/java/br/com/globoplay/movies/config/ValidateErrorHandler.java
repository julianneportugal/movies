package br.com.globoplay.movies.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.globoplay.models.ValidateError;

@RestControllerAdvice
public class ValidateErrorHandler {
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ValidateError handle(MethodArgumentTypeMismatchException exception) {
		ValidateError validateError = new ValidateError("Parâmetro inválido");
		return validateError;
	}
}
