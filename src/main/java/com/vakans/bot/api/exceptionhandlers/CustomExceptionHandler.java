package com.vakans.bot.api.exceptionhandlers;

import com.vakans.bot.api.domain.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.util.Collections.singletonList;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  //@ExceptionHandler(Exception.class)
  public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
    ErrorResponse error = new ErrorResponse("Server Error", singletonList(ex.getLocalizedMessage()));
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }




}
