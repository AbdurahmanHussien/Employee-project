package com.springboot.employeeproject.dao;

import com.springboot.employeeproject.dto.EmailDTO;
import com.springboot.employeeproject.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmailDAO extends JpaRepository<Email, Long> {

    @Query("select new com.springboot.employeeproject.dto.EmailDTO(e.id, e.name, e.value) from Email e")
    List<EmailDTO> findAllEmails();


   List <Email> findByName(String name);

    @Query("SELECT new com.springboot.employeeproject.dto.EmailDTO(e.id, e.name, e.value) FROM Email e WHERE e.value = :value")
  Optional <EmailDTO> findByValue(String value);

    @Query("SELECT new com.springboot.employeeproject.dto.EmailDTO(e.id, e.name, e.value) from Email e where e.name in :L ")
    List <EmailDTO> findByNameIn(List<String> L);
}
