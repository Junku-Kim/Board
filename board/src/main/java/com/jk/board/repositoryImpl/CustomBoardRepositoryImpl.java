package com.jk.board.repositoryImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jk.board.dto.BoardResponse;
import com.jk.board.paging.BoardCommonParams;
import com.jk.board.repository.CustomBoardRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CustomBoardRepositoryImpl implements CustomBoardRepository {

	@PersistenceContext
	private EntityManager em;
	
	/*
	 * 검색 키워드가 없을 때 게시글 수 조회 메서드
	 */
	public Long countBoardDefault() {
		String jpql = "SELECT COUNT(b) " +
					  "FROM Board b " +
					  "WHERE b.isDeleted = :isDeleted";
		
		Long result = em.createQuery(jpql, Long.class)
						.setParameter("isDeleted", false)
						.getSingleResult();
		
		return result;
	}
	
	/*
	 * 제목, 내용, 작성자를 모두 포함한 게시글 수 조회 메서드
	 */
	@Override
	public Long countBoardByAllSearchType(final String keyWordForSearch) {
		String jpql = "SELECT COUNT(b) " +
					  "FROM Board b " +
					  "WHERE b.isDeleted = :isDeleted " + 
					  "AND (b.title LIKE '%' || :keywordForSearch || '%' " +
					  "OR b.content LIKE '%' || :keywordForSearch || '%' " +
					  "OR b.writer LIKE '%' || :keywordForSearch || '%') ";
		
		Long result = em.createQuery(jpql, Long.class)
						.setParameter("isDeleted", false)
						.setParameter("keywordForSearch", keyWordForSearch)
						.getSingleResult();
		
		return result;
	}

	/*
	 * 단일 게시글 수 검색
	 */
	@Override
	public Long countBoardBySearchType(final String searchType, final String keywordForSearch) {
		return countBoardTemplate(searchType, keywordForSearch);
	}
	
	/*
	 * 게시글 수 검색 템플릿
	 */
	private Long countBoardTemplate(String searchType, String keywordForSearch) {
		String jpql = "SELECT COUNT(b) " +
					  "FROM Board b " +
					  "WHERE b.isDeleted = :isDeleted " +
					  "AND (CASE WHEN :searchType = 'title' THEN b.title " +
	                  "          WHEN :searchType = 'content' THEN TO_CHAR(b.content) " +
	                  "          WHEN :searchType = 'writer' THEN b.writer END) " +
	                  "LIKE '%' || :keywordForSearch || '%'";
		
		Long result = em.createQuery(jpql, Long.class)
						.setParameter("isDeleted", false)
						.setParameter("searchType", searchType)
						.setParameter("keywordForSearch", keywordForSearch)
						.getSingleResult();
	
		return result;
	}

	/*
	 * 검색 키워드가 없을 때 게시글 페이지네이션 조회 
	 */
	@Override
	public List<BoardResponse> findPageBoardsDefault(final BoardCommonParams params) {
		String jpql = "SELECT NEW com.jk.board.dto.BoardResponse(b) " +
					  "FROM Board b " +
					  "WHERE b.isDeleted = :isDeleted " +
					  "ORDER BY b.id DESC, b.createdDate DESC";
		
		List<BoardResponse> result = em.createQuery(jpql, BoardResponse.class)
										.setParameter("isDeleted", false)
										.setFirstResult(params.getPagination().getOffsetStart())
										.setMaxResults(params.getRecordPerPage())
										.getResultList();
		return result;
	}

	/*
	 * 검색 조건을 전체로 검색 시 게시글 조회 메서드
	 */
	@Override
	public List<BoardResponse> findPageBoardsByAllSearchType(final BoardCommonParams params) {
		String jpql = "SELECT NEW com.jk.board.dto.BoardResponse(b) " +
					  "FROM Board b " +
					  "WHERE b.isDeleted = :isDeleted " + 
					  "AND (b.title LIKE '%' || :keywordForSearch || '%' " +
					  "OR b.content LIKE '%' || :keywordForSearch || '%' " +
					  "OR b.writer LIKE '%' || :keywordForSearch || '%') " +
					  "ORDER BY b.id DESC, b.createdDate DESC";
		
		List<BoardResponse> result = em.createQuery(jpql, BoardResponse.class)
									   .setParameter("isDeleted", false)
									   .setParameter("keywordForSearch", params.getKeywordForSearch())
									   .setFirstResult(params.getPagination().getOffsetStart())
									   .setMaxResults(params.getRecordPerPage())
									   .getResultList();
		
		return result;
	}

	/*
	 * 단일 검색 타입으로 검색 시 게시글 조회 메서드
	 */
	@Override
	public List<BoardResponse> findPageBoardsBySearchType(final BoardCommonParams params) {
		return findPageBoardsTemplate(params);
	}

	/*
	 * 게시글 조회 템플릿
	 */
	private List<BoardResponse> findPageBoardsTemplate(final BoardCommonParams params) {
		String jpql = "SELECT NEW com.jk.board.dto.BoardResponse(b) " +
					  "FROM Board b " +
					  "WHERE b.isDeleted = :isDeleted " +
					  "AND (CASE WHEN :searchType = 'title' THEN b.title " +
	                  "          WHEN :searchType = 'content' THEN TO_CHAR(b.content) " +
	                  "          WHEN :searchType = 'writer' THEN b.writer END) " +
	                  "LIKE '%' || :keywordForSearch || '%'" +
	                  "ORDER BY b.id DESC, b.createdDate DESC";
		
		List<BoardResponse> result = em.createQuery(jpql, BoardResponse.class)
									   .setParameter("isDeleted", false)
									   .setParameter("searchType", params.getSearchType())
									   .setParameter("keywordForSearch", params.getKeywordForSearch())
									   .setFirstResult(params.getPagination().getOffsetStart())
									   .setMaxResults(params.getRecordPerPage())
									   .getResultList();
		
		return result;
	}
}
