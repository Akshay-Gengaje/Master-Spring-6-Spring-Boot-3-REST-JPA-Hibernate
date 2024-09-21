package com.darklord.school.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Contact {
    @NotBlank(message="Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    String name;
    @NotBlank(message = "Mobile number must not blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
    String mobileNum;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    String email;
    @NotBlank
    @Size(min =5, message = "Subject must be at least 5 characters")
    String subject;
    @NotBlank(message="Message must not be blank")
    @Size(min = 10, message = "Message must be at least 10 characters long")
    String message;
}
