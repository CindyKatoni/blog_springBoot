package com.example.blog_springboot_restapi.service.impl;

import com.example.blog_springboot_restapi.model.Post;
import com.example.blog_springboot_restapi.repository.PostRepository;
import com.example.blog_springboot_restapi.service.PostService;
import com.example.blog_springboot_restapi.payload.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //convert dto to post entity
        Post post = mapToEntity(postDto);

        //save the post object in db
        Post newPost = postRepository.save(post);

        //convert Post entity to DTO to return it to the controller
        PostDto postResponse = mapToDTO(newPost);

        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return null;
    }

    //create a private method that can be reused
    //convert entity to DTO

    private PostDto mapToDTO(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    //convert DTO to Entity
    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }


}
