package com.example.blog_springboot_restapi.service;

import java.lang.*;
import java.util.List;

import com.example.blog_springboot_restapi.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);


}
