package com.RESTAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RESTAPI.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
