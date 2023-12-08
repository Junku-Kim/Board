package com.jk.board.repository;

import java.util.List;

import com.jk.board.dto.BoardFileDTO;
import com.jk.board.dto.BoardFileOriginalName;

public interface CustomBoardFileRepository {

	List<BoardFileDTO> selectBoardFileDetail(Long boardId);

	List<BoardFileOriginalName> selectBoardFileOriginalName(Long boardId);
}
