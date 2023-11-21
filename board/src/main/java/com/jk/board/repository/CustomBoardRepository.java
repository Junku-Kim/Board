package com.jk.board.repository;

import java.util.List;

import com.jk.board.dto.BoardFileRequest;

public interface CustomBoardRepository {

	List<BoardFileRequest> selectBoardFileDetail(Long boardId);
}
