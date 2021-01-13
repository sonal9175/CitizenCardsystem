package com.login.error;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class UserGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	
	// Let Spring BasicErrorController handle the exception, we just override the status code
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException exception,WebRequest request){
    	ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false));
    	
    	return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    
    //Handling API exception
    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> handleAPIException(APIException exception,WebRequest request){
    	ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false));
    	
    	return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    
    //Handling global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception,WebRequest request){
    	ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false));
    	
    	return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
 // error handle for @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }
    
    
    
     

}
