package com.springboot.employeeproject.exceptions;

import com.springboot.employeeproject.dto.BundleMessageDTO;
import com.springboot.employeeproject.dto.ExceptionDTO;
import com.springboot.employeeproject.service.bundleService.BundleTranslatorService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {


    private BundleTranslatorService bundleTranslator;

    @Autowired
    public GlobalExceptionHandler(BundleTranslatorService bundleTranslator) {
        this.bundleTranslator = bundleTranslator;
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ExceptionDTO> handleException(SystemException ex) {
      BundleMessageDTO bundleMessageDTO = bundleTranslator.getBundleMessages(ex.getMessage());
        return ResponseEntity.ok(new ExceptionDTO(List.of(bundleMessageDTO)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> handleValidationException(MethodArgumentNotValidException ex) {

        List<BundleMessageDTO> messages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    String messageKey = error.getDefaultMessage();

                    return bundleTranslator.getBundleMessages(messageKey);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ExceptionDTO(messages));
    }


}
