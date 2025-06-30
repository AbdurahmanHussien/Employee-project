package com.springboot.employeeproject.service.employee;

import com.springboot.employeeproject.dao.EmployeeDAO;
import com.springboot.employeeproject.dto.BundleMessageDTO;
import com.springboot.employeeproject.dto.EmailDTO;
import com.springboot.employeeproject.dto.EmployeeDTO;
import com.springboot.employeeproject.mapper.EmployeeMapper;
import com.springboot.employeeproject.model.Employee;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final EmployeeMapper employeeMapper;
    private final MessageSource messageSource;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO , EmployeeMapper employeeMapper , MessageSource messageSource) {
        this.employeeDAO = employeeDAO;
        this.employeeMapper = employeeMapper;
        this.messageSource = messageSource;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeDAO.findAllEmployee();
        return mappingEmployees(employees);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) throws SystemException {
        validateEmployeeId(employeeDTO.getId());
        return getEmployeeDTO(employeeDTO);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws SystemException {
        if (Objects.isNull(employeeDTO.getId())) {
            throw new SystemException("error.id.notnull");
        }
        Employee employee = getEmployeeEntityById(employeeDTO.getId());
        return getEmployeeDTO(employeeDTO);
    }

    @Override
    public ResponseEntity<BundleMessageDTO> deleteEmployee(Long id) throws SystemException {
        Employee employee = getEmployeeEntityById(id);
        employeeDAO.deleteById(id);
       String messageEn = messageSource.getMessage("employee.deleted",null , Locale.ENGLISH);
        String messageAr = messageSource.getMessage("employee.deleted",null , new Locale("ar"));
        BundleMessageDTO response = new BundleMessageDTO(messageEn,messageAr);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    public List<EmployeeDTO> getAllEmployeesWithEmails() {
        List<Employee> employees = employeeDAO.findAllWithEmails();
        return employees.stream()
                .map(emp -> mapToEmployeeDTOWithEmails(emp))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO findEmployeeById(Long id) throws SystemException {
        Employee employee = getEmployeeEntityById(id);
        return mapToEmployeeDTOWithEmails(employee);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByListOfId(List<Long> ids) throws SystemException {
        List<Employee> employees = employeeDAO.findAllById(ids);
        if (employees.isEmpty()) {
            throw new SystemException("error.employee.notFound");
        }
        return employees.stream()
                .map(this::mapToEmployeeDTOWithEmails)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeesByListOfName(List<String> names) throws SystemException {
        List<Employee> employees = employeeDAO.findByNameIn(names);
        if (employees.isEmpty()) {
            throw new SystemException("error.employee.notFound");
        }
        return employees.stream()
                .map(this::mapToEmployeeDTOWithEmails)
                .collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeEntityById(Long id) throws SystemException {
        return employeeDAO.findById(id)
                .orElseThrow(() -> new SystemException("error.employee.notFound"));
    }

    private List<EmployeeDTO> mappingEmployees(List<Employee> employees) {
        return employees.stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    private EmployeeDTO getEmployeeDTO(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEmployee(employeeDTO);
        Employee savedEmployee = employeeDAO.save(employee);
        return employeeMapper.toEmployeeDTO(savedEmployee);
    }

    private void validateEmployeeId(Long id) throws SystemException {
        if (Objects.nonNull(id)) {
            throw new SystemException("error.id.invalid");
        }
    }

    private EmployeeDTO mapToEmployeeDTOWithEmails(Employee employee) {
        EmployeeDTO employeeDTO = employeeMapper.toEmployeeDTO(employee);
        List<EmailDTO> emailDtos = employee.getEmail().stream()
                .map(email -> new EmailDTO(email.getValue()))
                .collect(Collectors.toList());
        employeeDTO.setEmail(emailDtos);
        return employeeDTO;
    }
}
