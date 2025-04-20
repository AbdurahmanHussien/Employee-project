package com.springboot.employeeproject.service.employee;

import com.springboot.employeeproject.dto.EmployeeDTO;
import com.springboot.employeeproject.model.Employee;
import jakarta.transaction.SystemException;

import java.util.List;

public interface EmployeeService {



       List<EmployeeDTO> getAllEmployees();
       List<EmployeeDTO> getAllEmployeesWithEmails();
       EmployeeDTO createEmployee(EmployeeDTO employeeDTO) throws SystemException;
       EmployeeDTO updateEmployee (EmployeeDTO employeeDTO) throws SystemException;
       void deleteEmployee (Long id) throws SystemException;
       EmployeeDTO findEmployeeById (Long id) throws SystemException;
       List<EmployeeDTO> getEmployeesByListOfId ( List<Long> id) throws SystemException;
       List<EmployeeDTO> getEmployeesByListOfName ( List<String> name) throws SystemException;
       Employee getEmployeeEntityById(Long id) throws SystemException;





}
