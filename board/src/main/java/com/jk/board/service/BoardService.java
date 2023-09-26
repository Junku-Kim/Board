package com.jk.board.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.board.dto.BoardRequest;
import com.jk.board.dto.BoardResponse;
import com.jk.board.entity.Board;
import com.jk.board.exception.CustomException;
import com.jk.board.exception.ErrorCode;
import com.jk.board.repository.BoardRepository;


@Service
public class BoardService {

	private final BoardRepository boardRepository;
	
	public BoardService(final BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	/*
	 * 게시글 생성
	 */
	@Transactional
	public Long writeBoard(final BoardRequest boardRequest) {
		Board board = boardRepository.save(boardRequest.toEntity());
		
		return board.getId();
	}
	
	/*
	 * 게시글 리스트 조회
	 */
	public List<BoardResponse> findBoards() {
		Sort sort = Sort.by(Direction.DESC, "id", "createdDate");
		List<Board> list = boardRepository.findAll(sort);
		
		return list.stream().map(BoardResponse::new).toList();
	}
	
	/*
	 * 게시글 수정
	 */
	@Transactional
	public Long updateBoard(final Long id, BoardRequest boardRequest) {
		Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		board.update(boardRequest.getTitle(), boardRequest.getContent(), boardRequest.getWriter());
		
		return id;
	}
}
