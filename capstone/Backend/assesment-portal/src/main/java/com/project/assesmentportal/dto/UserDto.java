package com.project.assesmentportal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing a user.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    /**
     * The unique identifier of the user.
     */
    private long userId;

    /**
     * The first name of the user.
     */
    @NotBlank(message = "first name is required")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Enter a valid FirstName")
    private String firstName;

    /**
     * The last name of the user.
     */
    @NotBlank(message = "last name is required")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Enter a valid lastName")
    private String lastName;

    /**
     * The password of the user.
     */

    @NotBlank(message = "Password is required.")
    private String password;

    /**
     * The email address of the user.
     */
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@nucleusteq\\.com$", message =
            "Email should have @nucleusteq.com domain")
    private String email;

    /**
     * The phone number of the user.
     */
    @NotBlank(message = "Phone number is required.")
    @Pattern(regexp = "^\\d{10}$", message =
            "Phone number must be a 10-digit number.")
    private String phoneNumber;

    /**
     * The role of the user. Default value is "user".
     */
    private String role = "user";
}
