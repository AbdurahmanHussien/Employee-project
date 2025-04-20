package com.springboot.employeeproject.mapper;

import com.springboot.employeeproject.dto.EmailDTO;
import com.springboot.employeeproject.model.Email;
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
public class EmailMapperImpl implements EmailMapper {

    @Override
    public Email toEmail(EmailDTO emailDTO) {
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

    @Override
    public EmailDTO toEmailDTO(Email email) {
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

    @Override
    public List<EmailDTO> toEmailDTOList(List<Email> emailsList) {
        if ( emailsList == null ) {
            return null;
        }

        List<EmailDTO> list = new ArrayList<EmailDTO>( emailsList.size() );
        for ( Email email : emailsList ) {
            list.add( toEmailDTO( email ) );
        }

        return list;
    }

    @Override
    public List<Email> toEmailList(List<EmailDTO> emailsDTOList) {
        if ( emailsDTOList == null ) {
            return null;
        }

        List<Email> list = new ArrayList<Email>( emailsDTOList.size() );
        for ( EmailDTO emailDTO : emailsDTOList ) {
            list.add( toEmail( emailDTO ) );
        }

        return list;
    }
}
