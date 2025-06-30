package com.springboot.employeeproject.exceptions;


public class ApiException {

    private String messageEn;

    private String messageAr;

    public ApiException(String messageEn, String messageAr) {
        this.messageEn = messageEn;
        this.messageAr = messageAr;
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
}

