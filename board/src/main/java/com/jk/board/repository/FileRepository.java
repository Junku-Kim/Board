package com.jk.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.board.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {

}
