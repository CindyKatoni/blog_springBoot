package com.example.blog_springboot_restapi.service.impl;

import java.lang.*;
import java.util.List;
import java.util.stream.Collectors;

import com.example.blog_springboot_restapi.exception.BlogAPIException;
import com.example.blog_springboot_restapi.exception.ResourceNotFoundException;
import com.example.blog_springboot_restapi.model.Comment;
import com.example.blog_springboot_restapi.model.Post;
import com.example.blog_springboot_restapi.payload.CommentDto;
import com.example.blog_springboot_restapi.repository.CommentRepository;
import com.example.blog_springboot_restapi.repository.PostRepository;
import com.example.blog_springboot_restapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
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
    public List<CommentDto> getCommentsByPostId(Long postId) {
        //retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);
        //convert the List of comments entity to list of comments dto using stream(),
        // use map() to map entity to dto. Then collect the result to a list
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        //First retrieve post from database
        Post post = postRepository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        //Then Retrieve comment by id
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        //Check if comment belongs to the post. if it doesn't throw error.
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesnt belong to post");
        }
        //Else return the comment
        return mapToDto(comment);

    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        //Retrieve post entity from db
        Post post = postRepository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "id",postId));

        //Retrieve comment by commentid
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("Comment", "id",commentId));

        //Validate if comment belong to a particular post or not
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        //Set the updated comment details to Comment entity
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        //Save the comment entity
        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);
    }


    //convert entity to DTO
    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }
    //convert DTO to entity
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }



}
