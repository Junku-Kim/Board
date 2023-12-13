package com.jk.board.repositoryImpl;

import org.springframework.stereotype.Repository;

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
	public Long countBoardByAllSearchType(final String keywordForSearch) {
		String jpql = "SELECT COUNT(b) " +
					  "FROM Board b " +
					  "WHERE b.isDeleted = :isDeleted " + 
					  "AND (b.title LIKE '%' || :keywordForSearch || '%' " +
					  "OR b.content LIKE '%' || :keywordForSearch || '%' " +
					  "OR b.writer LIKE '%' || :keywordForSearch || '%') ";
		
		Long result = em.createQuery(jpql, Long.class)
						.setParameter("isDeleted", false)
						.setParameter("keywordForSearch", keywordForSearch)
						.getSingleResult();
		
		return result;
	}

	/*
	 * 제목 게시글 수 검색
	 */
	@Override
	public Long countBoardByTitle(final String keywordForSearch) {
		return countBoardTemplate("title", keywordForSearch);
	}

	/*
	 * 내용 게시글 수 검색
	 */
	@Override
	public Long countBoardByContent(final String keywordForSearch) {
		return countBoardTemplate("content", keywordForSearch);
	}

	/*
	 * 작성자 게시글 수 검색
	 */
	@Override
	public Long countBoardByWriter(final String keywordForSearch) {
		return countBoardTemplate("writer", keywordForSearch);
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
}
