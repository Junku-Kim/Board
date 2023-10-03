package com.jk.board.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	/*
	 * 게시글 리스트 조회 (삭제되지 않은)
	 */
	List<Board> findAllByIsDeleted(final boolean isDeleted, final Sort sort);
}
