package com.jk.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jk.board.domain.Board;
import com.jk.board.service.BoardService;

@Controller
public class BoardController {

	private final BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/board/write")
	public String boardWriteForm() {
		
		return "boardwrite";
	}
	
	@PostMapping("/board/writepro")
	public String boardWritePro(Board board) {
		
		boardService.write(board);
		
		return "redirect:/";
	}
}
