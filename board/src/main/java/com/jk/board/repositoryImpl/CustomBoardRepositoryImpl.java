package com.jk.board.repositoryImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jk.board.dto.BoardFileRequest;
import com.jk.board.entity.Board;
import com.jk.board.exception.CustomException;
import com.jk.board.exception.ErrorCode;
import com.jk.board.repository.BoardRepository;
import com.jk.board.repository.CustomBoardRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Repository
public class CustomBoardRepositoryImpl implements CustomBoardRepository {

	@PersistenceContext
    private EntityManager entityManager;
	
	private final BoardRepository boardRepository;
	
	/*
	 * 게시판 첨부파일 리스트
	 */
	@Override
	public List<BoardFileRequest> selectBoardFileDetail(Long boardId) {
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		String jpql = "SELECT NEW com.jk.board.dto.BoardFileRequest(" +
					  "boardFile.originalName, " +
					  "boardFile.savedName, " +
					  "boardFile.uploadDir, " +
					  "boardFile.extension, " +
					  "boardFile.size, " +
					  "boardFile.contentType, " +
					  "boardFile.board) " +
					  "FROM BoardFile boardFile " +
					  "WHERE boardFile.board = :board " +
					  "AND boardFile.isDeleted = :isDeleted";
		
		List<BoardFileRequest> result = entityManager.createQuery(jpql, BoardFileRequest.class)
				.setParameter("board", board)
				.setParameter("isDeleted", false)
				.getResultList();
		
		return result;
	}
}
