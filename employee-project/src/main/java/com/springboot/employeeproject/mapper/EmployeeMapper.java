package com.springboot.employeeproject.mapper;

import com.springboot.employeeproject.dto.EmployeeDTO;
import com.springboot.employeeproject.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {



    @Mapping(source = "name", target = "name")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "salary", target = "salary")
    @Mapping(source = "email", target = "email")
    Employee toEmployee(EmployeeDTO employeeDTO);


    @Mapping(source = "name", target = "name")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "salary", target = "salary")
    @Mapping(source = "email", target = "email")
    EmployeeDTO toEmployeeDTO(Employee employee);

    List< EmployeeDTO> toEmployeeDTOList(List<Employee> employeesList);

    List <Employee> toEmployeeList(List<EmployeeDTO> employeeDTOList);
}
