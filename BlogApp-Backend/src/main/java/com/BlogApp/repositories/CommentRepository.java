package com.BlogApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApp.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
