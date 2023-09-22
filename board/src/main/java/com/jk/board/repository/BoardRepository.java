package com.jk.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	//Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}
