package com.springboot.employeeproject.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BundleMessageDTO {

    @JsonProperty("message_en")
    private String messageEn;

    @JsonProperty("message_ar")
    private String messageAr;

    public BundleMessageDTO(String messageEn, String messageAr) {
        this.messageEn = messageEn;
        this.messageAr = messageAr;
    }
}
