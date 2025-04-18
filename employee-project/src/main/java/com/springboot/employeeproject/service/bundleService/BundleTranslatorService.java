package com.springboot.employeeproject.service.bundleService;


import com.springboot.employeeproject.dto.BundleMessageDTO;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class BundleTranslatorService {

    private static ResourceBundleMessageSource messageSource;

    public BundleTranslatorService (ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static BundleMessageDTO getBundleMessages(String key) {
          return new BundleMessageDTO(
                  messageSource.getMessage(key,null , new Locale("en")),
                  messageSource.getMessage(key,null , new Locale("ar"))
          );
    }


}
