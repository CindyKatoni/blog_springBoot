package com.example.blog_springboot_restapi.service.impl;

import com.example.blog_springboot_restapi.model.Post;
import com.example.blog_springboot_restapi.repository.PostRepository;
import com.example.blog_springboot_restapi.service.PostService;
import com.example.blog_springboot_restapi.payload.PostDto;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //convert dto to post entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        //save the post object in db
        Post newPost = postRepository.save(post);

        //convert Post entity to DTO to return it to the controller
        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }
}
