package com.jk.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.board.entity.BoardFile;

public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {

}
