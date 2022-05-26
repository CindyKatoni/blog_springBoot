package com.example.blog_springboot_restapi.service;

import java.lang.*;
import java.util.List;

import com.example.blog_springboot_restapi.model.Comment;
import com.example.blog_springboot_restapi.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(Long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(Long postId);

    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updateComment(Long postId, Long commentId);
}
