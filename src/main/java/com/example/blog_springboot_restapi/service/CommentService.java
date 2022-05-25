package com.example.blog_springboot_restapi.service;

import com.example.blog_springboot_restapi.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto);
}
