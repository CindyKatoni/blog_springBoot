package com.example.blog_springboot_restapi.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private Long Id;

    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Enter correct email")
    private String email;
    @NotEmpty
    @Size(min = 10, message = "Body should contain more than 10 characters")
    private String body;
}
