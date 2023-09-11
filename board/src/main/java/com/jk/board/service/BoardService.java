package com.jk.board.service;

import org.springframework.stereotype.Service;

import com.jk.board.domain.Board;
import com.jk.board.repository.BoardRepository;

@Service
public class BoardService {

	private final BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	public void write(Board board) {
		boardRepository.save(board);
	}
}
