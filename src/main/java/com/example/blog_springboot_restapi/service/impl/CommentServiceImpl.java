package com.example.blog_springboot_restapi.service.impl;

import java.lang.*;
import java.util.List;
import java.util.stream.Collectors;

import com.example.blog_springboot_restapi.exception.ResourceNotFoundException;
import com.example.blog_springboot_restapi.model.Comment;
import com.example.blog_springboot_restapi.model.Post;
import com.example.blog_springboot_restapi.payload.CommentDto;
import com.example.blog_springboot_restapi.repository.CommentRepository;
import com.example.blog_springboot_restapi.repository.PostRepository;
import com.example.blog_springboot_restapi.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        //Convert commentDto to Comment Entity
        Comment comment = mapToEntity(commentDto);

        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        //Set post to comment entity
        comment.setPost(post);

        // save comment entity to DB
        Comment newComment =  commentRepository.save(comment);

        return mapToDto(newComment);

    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        //retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);
        //convert the List of comments entity to list of comments dto using stream(),
        // use map() to map entity to dto. Then collect the result to a list
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }


    //convert entity to DTO
    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }
    //convert DTO to entity
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }



}
