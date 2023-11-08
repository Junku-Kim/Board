package com.jk.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jk.board.dto.BoardResponse;
import com.jk.board.paging.BoardCommonParams;

@Mapper
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
