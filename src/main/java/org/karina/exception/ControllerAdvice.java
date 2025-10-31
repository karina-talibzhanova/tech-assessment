package org.karina.exception;

import lombok.extern.slf4j.Slf4j;
import org.karina.factory.PrimeNumberCalculatorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleHttpClientErrorException(HttpClientErrorException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.BAD_REQUEST.toString()).message(ex.getMessage()).build();
        log.error("HttpClientErrorException: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.BAD_REQUEST.toString()).message(ex.getMessage()).build();
        log.error("IllegalArgumentException: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        List<String> validAlgorithms = new ArrayList<>();
        for (PrimeNumberCalculatorType type : PrimeNumberCalculatorType.values()) {
            validAlgorithms.add(type.toString());
        }
        ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.BAD_REQUEST.toString()).message("Invalid algorithm. Please choose one of the following: " + validAlgorithms).build();
        log.error("MethodArgumentTypeMismatchException: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString()).message("Something went wrong!").build();
        log.error("Exception: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
