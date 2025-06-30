package com.springboot.employeeproject.mapper;

import com.springboot.employeeproject.dto.EmailDTO;
import com.springboot.employeeproject.model.Email;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    Email toEmail(EmailDTO emailDTO);

    EmailDTO toEmailDTO(Email email);

    List<EmailDTO> toEmailDTOList(List<Email> emailsList);

    List <Email> toEmailList( List<EmailDTO> emailsDTOList);

}
