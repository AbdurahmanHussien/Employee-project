package com.springboot.employeeproject.dto;

import java.util.List;


public class ExceptionDTO {

    private List<BundleMessageDTO> messages;

    public ExceptionDTO(List<BundleMessageDTO> messages) {
        this.messages = messages;
    }

    public List<BundleMessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<BundleMessageDTO> messages) {
        this.messages = messages;
    }
}
