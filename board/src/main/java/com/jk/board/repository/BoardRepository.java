package com.jk.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, CustomBoardRepository {

}
