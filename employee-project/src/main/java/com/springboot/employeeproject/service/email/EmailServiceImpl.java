package com.springboot.employeeproject.service.email;

import com.springboot.employeeproject.dao.EmailDAO;
import com.springboot.employeeproject.dto.EmailDTO;
import com.springboot.employeeproject.mapper.EmailMapper;
import com.springboot.employeeproject.model.Email;
import com.springboot.employeeproject.model.Employee;
import com.springboot.employeeproject.service.employee.EmployeeService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailDAO emailDAO;
    private final EmailMapper emailMapper;
    private final EmployeeService employeeService;

    @Autowired
    public EmailServiceImpl(EmailDAO emailDAO, EmailMapper emailMapper, EmployeeService employeeService) {
        this.emailDAO = emailDAO;
        this.emailMapper = emailMapper;
        this.employeeService = employeeService;
    }

    @Override
    public List<EmailDTO> getAllEmails() {
        return emailDAO.findAllEmails();
    }

    @Override
    public EmailDTO createEmail(EmailDTO emailDto) throws SystemException {
        validateEmailId(emailDto.getId() , emailDto.getValue());
        return saveEmail(emailDto);
    }

    @Override
    public EmailDTO updateEmail(EmailDTO emailDto) throws SystemException {
        if (Objects.isNull(emailDto.getId())) {
            throw new SystemException("error.id.notnull");
        }
        return saveEmail(emailDto);
    }

    @Override
    public void deleteEmail(Long id) throws SystemException {
        Optional<Email> email = emailDAO.findById(id);
        email.ifPresentOrElse(
                e -> emailDAO.deleteById(id),
                () -> {
                    try {
                        throw new SystemException("error.email.notFound");
                    } catch (SystemException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @Override
    public List<EmailDTO> getEmailsByName(String name) throws SystemException {
        List<Email> emails = emailDAO.findByName(name);
        return emails.stream().map(emailMapper::toEmailDTO).collect(Collectors.toList());
    }

    @Override
    public EmailDTO getEmailByValue(String value) throws SystemException {
        return emailDAO.findByValue(value)
                .orElseThrow(() -> new SystemException("error.email.notFound"));
    }

    @Override
    public List<EmailDTO> getEmailsByListOfName(List<String> names) throws SystemException {
        return emailDAO.findByNameIn(names);
    }

    private void validateEmailId(Long id , String value) throws SystemException {
        if (Objects.nonNull(id)) {
            throw new SystemException("error.id.invalid");
        }
        Optional<EmailDTO> existingEmail = emailDAO.findByValue(value);

        // إذا كان البريد الإلكتروني موجود بالفعل، ارمِ استثناء برسالة مناسبة
        if (existingEmail.isPresent()) {
            throw new SystemException("error.email.exists");
        }
    }



    private EmailDTO saveEmail(EmailDTO emailDto) throws SystemException {
        Employee employee = employeeService.getEmployeeEntityById(emailDto.getEmployeeId());
        if (employee == null) {
            throw new SystemException("error.employee.notFound");
        }

        Email email = new Email();
        email.setName(emailDto.getName());
        email.setValue(emailDto.getValue());
        email.setEmployee(employee);

        Email savedEmail = emailDAO.save(email);

        emailDto.setName(savedEmail.getName());
        emailDto.setValue(savedEmail.getValue());
        emailDto.setEmployeeId(savedEmail.getEmployee().getId());

        return emailDto;
    }
}
