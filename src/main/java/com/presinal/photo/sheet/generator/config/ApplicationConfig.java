package com.presinal.photo.sheet.generator.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Miguel Presinal <presinal378@gmail.com>
 */
@Configuration
@ComponentScan(basePackages = {
        "com.presinal.photo.sheet.generator.component",
        "com.presinal.photo.sheet.generator.service"
})
public class ApplicationConfig {
}
