package com.jk.board.repository;

public interface CustomBoardRepository {
	Long countBoardDefault();
	
	Long countBoardByAllSearchType(final String keywordForSearch);
	
	Long countBoardByTitle(final String keywordForSearch);
	
	Long countBoardByContent(final String keywordForSearch);
	
	Long countBoardByWriter(final String keywordForSearch);
}
