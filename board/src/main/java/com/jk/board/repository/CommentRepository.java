package com.jk.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.board.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
