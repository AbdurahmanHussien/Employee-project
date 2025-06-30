package com.springboot.employeeproject.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BundleMessageDTO {

    private String field;

    private String messageEn;

    private String messageAr;


    public BundleMessageDTO(String field, String messageEn, String messageAr) {
        this.field = field;
        this.messageEn = messageEn;
        this.messageAr = messageAr;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessageEn() {
        return messageEn;
    }

    public void setMessageEn(String messageEn) {
        this.messageEn = messageEn;
    }

    public String getMessageAr() {
        return messageAr;
    }

    public void setMessageAr(String messageAr) {
        this.messageAr = messageAr;
    }

    public BundleMessageDTO(String messageEn, String messageAr) {
        this.messageEn = messageEn;
        this.messageAr = messageAr;
    }
}
