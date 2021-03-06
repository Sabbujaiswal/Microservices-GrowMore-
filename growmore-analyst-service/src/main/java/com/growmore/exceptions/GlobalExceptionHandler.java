package com.growmore.exceptions;


import com.growmore.model.ApiErrors;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        String error = "Method not allowed";
        ApiErrors errors = new ApiErrors(timestamp, message, status.value(), error);
        return ResponseEntity.status(status).body(errors);
    }

    /**
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        String error = "Invalid Media Type";
        ApiErrors errors = new ApiErrors(timestamp, message, status.value(), error);
        return ResponseEntity.status(status).body(errors);
    }

    /**
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        String error = "Path Variable is missing";
        ApiErrors errors = new ApiErrors(timestamp, message, status.value(), error);
        return ResponseEntity.status(status).body(errors);
    }

    /**
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        String error = "Request Parameter is missing";
        ApiErrors errors = new ApiErrors(timestamp, message, status.value(), error);
        return ResponseEntity.status(status).body(errors);
    }

    /**
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        String error = "Type mismatch";
        ApiErrors errors = new ApiErrors(timestamp, message, status.value(), error);
        return ResponseEntity.status(status).body(errors);
    }

    /**
     *
     * @param ex
     * @return
     */

    @ExceptionHandler(AnalystNotFoundException.class)
    protected ResponseEntity<Object> handleProductNotFound(AnalystNotFoundException ex) {
        String message = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        String error = "Analyst Not Found";
        ApiErrors errors = new ApiErrors(timestamp, message, HttpStatus.CONFLICT.value(), error);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
    }

    /**
     *
     * @param ex
     * @return
     */

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleOtherException(Exception ex) {
        String message = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        String error = "Other Exception";
        ApiErrors errors = new ApiErrors(timestamp, message, HttpStatus.BAD_GATEWAY.value(), error);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errors);
    }
}
