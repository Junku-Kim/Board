package com.jk.board.repository;

import java.util.List;

import com.jk.board.dto.BoardFileDTO;
import com.jk.board.dto.BoardFileOriginalName;

public interface CustomBoardRepository {

	List<BoardFileDTO> selectBoardFileDetail(Long boardId);
	
	//List<String> selectBoardFileOriginalName(Long boardId);
	
	List<BoardFileOriginalName> selectBoardFileOriginalName(Long boardId);
}
