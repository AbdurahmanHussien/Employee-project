package com.springboot.employeeproject.controller;


import com.springboot.employeeproject.dto.EmailDTO;
import com.springboot.employeeproject.dto.EmployeeDTO;
import com.springboot.employeeproject.service.email.EmailService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/Emails")
public class EmailController {

         private EmailService emailService;

         @Autowired
         public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public ResponseEntity<List<EmailDTO>> getAllEmails() {
        return ResponseEntity.ok(emailService.getAllEmails());
    }
    @PostMapping("/createEmail")
    public ResponseEntity  <EmailDTO>  createEmail(@RequestBody @Valid EmailDTO emailDto) throws SystemException {
        return ResponseEntity.created(URI.create("/createEmail")).body(emailService.createEmail(emailDto));
    }

    @PutMapping("/updateEmail")
    public ResponseEntity <EmailDTO>  updateEmail(@RequestBody EmailDTO emailDto) throws SystemException {
             return ResponseEntity.ok(emailService.updateEmail(emailDto));
    }
    @DeleteMapping("/deleteEmail/{id}")
    public ResponseEntity <EmailDTO>  deleteEmail( @PathVariable long id) throws SystemException {
             try {
                 emailService.deleteEmail(id);
                 return ResponseEntity.noContent().build();
             } catch (RuntimeException e) {
                 return ResponseEntity.notFound().build();
             }

        }
    @GetMapping("/getByName/{name}")
    public ResponseEntity <List<EmailDTO>> getEmailByName( @PathVariable String name) throws SystemException {
        return ResponseEntity.ok(emailService.getEmailsByName(name));

    }

    @GetMapping("/getByValue/{value}")
    public ResponseEntity <EmailDTO> getEmailByValue( @PathVariable String value) throws SystemException {
        return ResponseEntity.ok(emailService.getEmailByValue(value));

    }
    @GetMapping("/by-names")
    //GET /employees/by-names?names=  ,  ,
    public ResponseEntity<List<EmailDTO>> getEmployeesByNames(@RequestParam List<String> names) throws SystemException {
        List<EmailDTO> emails = emailService.getEmailsByListOfName(names);
        return ResponseEntity.ok(emails);
    }

}
