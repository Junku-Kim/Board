package com.jk.board.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jk.board.dto.BoardRequest;
import com.jk.board.dto.BoardResponse;
import com.jk.board.paging.CommonParams;
import com.jk.board.service.BoardService;

@RequestMapping("/api")
@RestController
public class BoardApiController {

	private final BoardService boardService;

	public BoardApiController(final BoardService boardService) {
		this.boardService = boardService;
	}

	/*
	 * 게시글 생성
	 */
	@PostMapping("/boards")
	public Long writeBoard(@RequestBody final BoardRequest boardRequest) {
		return boardService.writeBoard(boardRequest);
	}

	/*
	 * 게시글 수정
	 */
	@PatchMapping("/boards/{id}")
	public Long updateBoard(@PathVariable final Long id, @RequestBody final BoardRequest boardRequest) {
		return boardService.updateBoard(id, boardRequest);
	}
	
	/*
	 * 게시글 삭제
	 */
	@DeleteMapping("/boards/{id}")
	public Long deleteBoard(@PathVariable final Long id) {
		return boardService.deleteBoard(id);
	}
	
	/*
	 * 게시글 리스트 조회
	 */
	@GetMapping("/boards")
	public Map<String, Object> findBoards(final CommonParams params) {
		return boardService.findAllBoards(params);
	}

	/*
	 * 게시글 상세정보 조회
	 */
	@GetMapping("/boards/{id}")
	public BoardResponse findBoardById(@PathVariable final Long id) {
		return boardService.findBoardById(id);
	}
}
