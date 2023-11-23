package com.jk.board.repositoryImpl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jk.board.dto.BoardFileRequest;
import com.jk.board.repository.CustomBoardRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CustomBoardRepositoryImpl implements CustomBoardRepository {

	@PersistenceContext
    private EntityManager entityManager;
	
	/*
	 * 게시판 첨부파일 리스트
	 */
	@Override
	public List<BoardFileRequest> selectBoardFileDetail(Long boardId) {
		
		return Collections.emptyList();
	}
}
