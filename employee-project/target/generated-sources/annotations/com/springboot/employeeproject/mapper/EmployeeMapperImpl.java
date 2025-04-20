package com.springboot.employeeproject.mapper;

import com.springboot.employeeproject.dto.EmailDTO;
import com.springboot.employeeproject.dto.EmployeeDTO;
import com.springboot.employeeproject.model.Email;
import com.springboot.employeeproject.model.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-20T21:23:10+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toEmployee(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setName( employeeDTO.getName() );
        employee.setAge( employeeDTO.getAge() );
        employee.setSalary( employeeDTO.getSalary() );
        employee.setEmail( emailDTOListToEmailList( employeeDTO.getEmail() ) );
        employee.setId( employeeDTO.getId() );

        return employee;
    }

    @Override
    public EmployeeDTO toEmployeeDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setName( employee.getName() );
        employeeDTO.setAge( employee.getAge() );
        employeeDTO.setSalary( employee.getSalary() );
        employeeDTO.setEmail( emailListToEmailDTOList( employee.getEmail() ) );
        employeeDTO.setId( employee.getId() );

        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> toEmployeeDTOList(List<Employee> employeesList) {
        if ( employeesList == null ) {
            return null;
        }

        List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( employeesList.size() );
        for ( Employee employee : employeesList ) {
            list.add( toEmployeeDTO( employee ) );
        }

        return list;
    }

    @Override
    public List<Employee> toEmployeeList(List<EmployeeDTO> employeeDTOList) {
        if ( employeeDTOList == null ) {
            return null;
        }

        List<Employee> list = new ArrayList<Employee>( employeeDTOList.size() );
        for ( EmployeeDTO employeeDTO : employeeDTOList ) {
            list.add( toEmployee( employeeDTO ) );
        }

        return list;
    }

    protected Email emailDTOToEmail(EmailDTO emailDTO) {
        if ( emailDTO == null ) {
            return null;
        }

        Email email = new Email();

        email.setId( emailDTO.getId() );
        email.setName( emailDTO.getName() );
        email.setValue( emailDTO.getValue() );
        email.setEmployee( emailDTO.getEmployee() );

        return email;
    }

    protected List<Email> emailDTOListToEmailList(List<EmailDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Email> list1 = new ArrayList<Email>( list.size() );
        for ( EmailDTO emailDTO : list ) {
            list1.add( emailDTOToEmail( emailDTO ) );
        }

        return list1;
    }

    protected EmailDTO emailToEmailDTO(Email email) {
        if ( email == null ) {
            return null;
        }

        EmailDTO emailDTO = new EmailDTO();

        emailDTO.setId( email.getId() );
        emailDTO.setName( email.getName() );
        emailDTO.setValue( email.getValue() );
        emailDTO.setEmployee( email.getEmployee() );

        return emailDTO;
    }

    protected List<EmailDTO> emailListToEmailDTOList(List<Email> list) {
        if ( list == null ) {
            return null;
        }

        List<EmailDTO> list1 = new ArrayList<EmailDTO>( list.size() );
        for ( Email email : list ) {
            list1.add( emailToEmailDTO( email ) );
        }

        return list1;
    }
}
