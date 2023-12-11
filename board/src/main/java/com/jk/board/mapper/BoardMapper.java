package com.jk.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jk.board.dto.BoardResponse;
import com.jk.board.paging.BoardCommonParams;

@Mapper
/*
 * MyBatis와 연결하기 위한 BoardMapper 인터페이스입니다.
 */
public interface BoardMapper {

	/*
	 * 게시글 수 조회
	 */
	int countBoard(final BoardCommonParams params);
	
	/*
	 * 게시글 리스트 조회
	 */
	List<BoardResponse> findAllBoards(final BoardCommonParams params);
}
