package ee.bcs.valiit.tasks.solution.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BankControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object>handleError(Exception ex) {
        System.out.println("Handle exeption here.");
        ErrorMessage response = new ErrorMessage();
        response.setMessage(ex.getMessage());
        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    }


