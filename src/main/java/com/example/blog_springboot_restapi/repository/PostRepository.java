package com.example.blog_springboot_restapi.repository;

import com.example.blog_springboot_restapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
