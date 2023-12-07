package com.jk.board.repositoryImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jk.board.dto.BoardFileDTO;
import com.jk.board.dto.BoardFileOriginalName;
import com.jk.board.repository.CustomBoardRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CustomBoardRepositoryImpl implements CustomBoardRepository {

	@PersistenceContext
    private EntityManager entityManager;
	
	
	/*
	 * 게시판 첨부파일 리스트
	 */
	@Override
	public List<BoardFileDTO> selectBoardFileDetail(final Long boardId) {
		String jpql = "SELECT NEW com.jk.board.dto.BoardFileDTO(" +
					  "boardFile.id, " +
					  "boardFile.originalName, " +
					  "boardFile.savedName, " +
					  "boardFile.uploadDir, " +
					  "boardFile.extension, " +
					  "boardFile.size, " +
					  "boardFile.contentType, " +
					  "boardFile.board) " +
					  "FROM BoardFile boardFile " +
					  "WHERE boardFile.board.id = :boardId " +
					  "AND boardFile.isDeleted = :isDeleted";
		
		List<BoardFileDTO> result = entityManager.createQuery(jpql, BoardFileDTO.class)
				.setParameter("boardId", boardId)
				.setParameter("isDeleted", false)
				.getResultList();
		
		return result;
	}
	
	/*
	 * 게시글 수정 시 첨부파일 이름을 표시하기 위한 리스트
	 */
	public List<BoardFileOriginalName> selectBoardFileOriginalName(final Long boardId) {
		String jpql = "SELECT NEW com.jk.board.dto.BoardFileOriginalName(" +
					  "bf.id, " +
					  "bf.originalName) " +
					  "FROM BoardFile bf " +
					  "WHERE bf.board.id = :boardId " +
					  "AND bf.isDeleted = :isDeleted";
		
		List<BoardFileOriginalName> result = entityManager.createQuery(jpql, BoardFileOriginalName.class)
				.setParameter("boardId", boardId)
				.setParameter("isDeleted", false)
				.getResultList();
		
		return result;
	}
}
