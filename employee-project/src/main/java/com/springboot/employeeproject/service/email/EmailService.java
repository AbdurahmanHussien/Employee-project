package com.springboot.employeeproject.service.email;

import com.springboot.employeeproject.dto.EmailDTO;
import jakarta.transaction.SystemException;

import java.util.List;

public interface EmailService {

    List<EmailDTO> getAllEmails();
    EmailDTO createEmail(EmailDTO emailDto) throws SystemException;
    EmailDTO updateEmail (EmailDTO emailDto) throws SystemException;
    void deleteEmail(Long id) throws SystemException;
     List <EmailDTO> getEmailsByName (String name) throws SystemException;
    EmailDTO  getEmailByValue (String value) throws SystemException;
    List <EmailDTO> getEmailsByListOfName (List <String> name) throws SystemException;

}
