package com.example.blog_springboot_restapi.service;

import com.example.blog_springboot_restapi.payload.PostDto;

import java.util.List;
import java.util.Map;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();
//    Map<String, String> getAllPosts();

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);
}
