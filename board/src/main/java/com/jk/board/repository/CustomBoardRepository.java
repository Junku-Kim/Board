package com.jk.board.repository;

import java.util.List;

import com.jk.board.dto.BoardResponse;
import com.jk.board.paging.BoardCommonParams;

public interface CustomBoardRepository {
	Long countBoardDefault();
	
	// 매개변수의 개수가 적기 때문에 가독성이 좋으므로 리소스적인 측면에서 필요한 필드 값을 보낸다. 
	Long countBoardByAllSearchType(final String keywordForSearch);
	
	Long countBoardBySearchType(final String SearchType, final String keywordForSearch);
	
	// 매개변수가 많기 때문에 가독성을 위해 객체 자체를 보낸다.
	List<BoardResponse> findPageBoardsDefault(final BoardCommonParams params);
	
	List<BoardResponse> findPageBoardsByAllSearchType(final BoardCommonParams params);
	
	List<BoardResponse> findPageBoardsBySearchType(final BoardCommonParams params);
}
