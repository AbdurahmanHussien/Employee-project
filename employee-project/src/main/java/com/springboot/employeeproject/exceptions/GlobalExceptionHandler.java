package com.springboot.employeeproject.exceptions;

import com.springboot.employeeproject.dto.BundleMessageDTO;
import com.springboot.employeeproject.dto.ExceptionDTO;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {


    private MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private String getMessage(String key , Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ApiException> handleException(SystemException ex) {
        String key = ex.getMessage();
        String messageEn = getMessage(key , Locale.ENGLISH);
        String messageAr=getMessage(key , new Locale("ar"));
        return new ResponseEntity<>(new ApiException(messageEn, messageAr), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> handleValidationException(MethodArgumentNotValidException ex) {

        List<BundleMessageDTO> messages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    String key = error.getDefaultMessage(); // لازم تكتب message=key في @NotBlank
                    String field = error.getField();

                    String messageEn = messageSource.getMessage(key, null, Locale.ENGLISH);
                    String messageAr = messageSource.getMessage(key, null, new Locale("ar"));

                    return new BundleMessageDTO(field, messageEn, messageAr);
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ExceptionDTO(messages), HttpStatus.BAD_REQUEST);
    }


}
