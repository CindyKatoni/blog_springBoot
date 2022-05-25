package com.example.blog_springboot_restapi.payload;

import lombok.Data;

@Data
public class CommentDto {
    private Long Id;
    private String name;
    private String email;
    private String messageBody;
}
