package com.example.blog_springboot_restapi.service;

import com.example.blog_springboot_restapi.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();
}
