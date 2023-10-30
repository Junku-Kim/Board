package com.jk.board.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.board.entity.Board;
import com.jk.board.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	public List<Comment> findAllByBoard(Board board, Sort sort);
}
