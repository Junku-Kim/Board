package com.jk.board.repositoryImpl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jk.board.dto.BoardFileRequest;
import com.jk.board.repository.CustomBoardRepository;

import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Repository
public class CustomBoardRepositoryImpl implements CustomBoardRepository {

    private final EntityManager entityManager;
	
	/*
	 * 게시판 첨부파일 리스트
	 */
	@Override
	public List<BoardFileRequest> selectBoardFileDetail(Long boardId) {
		
		return Collections.emptyList();
	}
}
