package com.example.blog_springboot_restapi.repository;

import com.example.blog_springboot_restapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
