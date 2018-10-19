package com.bestapp.bets.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class BestAppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(Exception ex,
                                                             WebRequest request) {
        BetsAppExceptionResponse betsAppExceptionResponse =
                new BetsAppExceptionResponse(LocalDateTime.now(), ex.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity<>(betsAppExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<String> mess = ex.getBindingResult().getAllErrors().stream().map(
                DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        BetsAppExceptionResponse betsAppExceptionResponse =
                new BetsAppExceptionResponse(LocalDateTime.now(), mess.toString(),
                        request.getDescription(false));
        return new ResponseEntity<>(betsAppExceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
