package com.project.assesmentportal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The DTO class representing the Login Request in the assessment
 * platform.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    /**
     * The Email Address of the User.
     */
    @NotBlank(message = "Email is required")
    private String email;
    /**
     * The Password of the User.
     */
    @NotBlank(message = "Password is required.")
    private String password;
}
