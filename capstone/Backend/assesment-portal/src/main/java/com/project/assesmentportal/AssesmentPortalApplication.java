package com.project.assesmentportal;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * This is the main class
 */
@SpringBootApplication
public class AssesmentPortalApplication {

    /**
     * This is the main function of the application.
     * @param args argument of main class.
     */
    public static void main(String[] args) {
        SpringApplication.run(AssesmentPortalApplication.class, args);
    }

    /**
     * This is the modelmapper bean
     * @return ModelMapper bean.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
