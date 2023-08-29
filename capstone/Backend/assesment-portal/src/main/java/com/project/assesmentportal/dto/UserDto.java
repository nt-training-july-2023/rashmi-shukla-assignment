package com.project.assesmentportal.dto;

import lombok.Data;

@Data
public class UserDto {
    private long userId;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private long phoneNumber;

    private String role = "user";
}
