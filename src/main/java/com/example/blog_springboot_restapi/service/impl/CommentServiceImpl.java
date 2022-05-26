package com.example.blog_springboot_restapi.service.impl;

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

    public CommentDto createComment(Long postId, CommentDto commentDto){
        //Convert commentDto to Comment Entity
        Comment comment = mapToEntity(commentDto);

        //Retrieve Post Entity by id
        Post post = postRepository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        //Set post to comment entity
        comment.setPost(post);

        //save comment entity to the db

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);

    }

    //convert entity to DTO
    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setMessageBody(comment.getMessageBody());
        return commentDto;
    }
    //convert DTO to entity
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setMessageBody(commentDto.getMessageBody());
        return comment;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        return null;
    }
}
