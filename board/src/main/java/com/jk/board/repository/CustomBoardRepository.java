package com.jk.board.repository;

import java.util.List;

import com.jk.board.dto.BoardFileDTO;

public interface CustomBoardRepository {

	List<BoardFileDTO> selectBoardFileDetail(Long boardId);
}
