package com.project.assesmentportal;

/**
 * This is the main class
 */
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AssesmentPortalApplication {
    /**
     * 
     * @param args argument of main class
     */
    public static void main(String[] args) {
        SpringApplication.run(AssesmentPortalApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
