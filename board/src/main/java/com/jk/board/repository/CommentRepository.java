package com.jk.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.board.entity.Board;
import com.jk.board.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	Page<Comment> findAllByBoardAndIsDeleted(Board board, boolean isDeleted, Pageable pageable);
}
