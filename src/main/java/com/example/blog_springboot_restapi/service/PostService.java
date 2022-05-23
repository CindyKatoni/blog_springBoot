package com.example.blog_springboot_restapi.service;

import com.example.blog_springboot_restapi.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
