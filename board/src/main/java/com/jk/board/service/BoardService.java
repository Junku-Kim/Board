package com.jk.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.board.domain.Board;
import com.jk.board.repository.BoardRepository;

@Transactional
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	// 게시글 작성 처리
	public void boardWrite(Board board) {
		boardRepository.save(board);
	}
	
	// 게시글 리스트 처리
	public List<Board> boardList() {
		
		return boardRepository.findAll();
	}
	
	// 특정 게시글 불러오기
	public Optional<Board> boardView(Long id) {
		
		return boardRepository.findById(id);
	}
	
	// 특정 게시글 삭제
	public void boardDelete(Long id) {
		boardRepository.deleteById(id);
	}
}
