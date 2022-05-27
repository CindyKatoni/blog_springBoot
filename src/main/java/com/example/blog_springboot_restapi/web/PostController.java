package com.example.blog_springboot_restapi.web;

import com.example.blog_springboot_restapi.payload.PostDto;
import com.example.blog_springboot_restapi.payload.PostResponse;
import com.example.blog_springboot_restapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create blog post rest-api
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //Get all posts
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false)int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false)String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false)String sortDir){

        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    //Get post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //update post by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id){
       PostDto postResponse = postService.updatePost(postDto, id);

       return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

}
