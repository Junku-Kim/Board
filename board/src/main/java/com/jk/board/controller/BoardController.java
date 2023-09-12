package com.jk.board.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		return "board-write";
	}
	
	/*
	 * rediect:를 하면 브라우저에서 페이지를 새로 고치고 새 URL로 이동하게 된다.
	 * 하지 않는 다면 브라우저 주소 표시줄에는 현재 요청한 URL이 남아있고 페이지를 다시 로드하지 않는다.
	 */
	@PostMapping("/board/writepro")
	public String boardWritePro(Board board) {
		
		boardService.boardWrite(board);
		
		return "redirect:/";
	}
	
	@GetMapping("/board/list")
	public String boardList(Model model) {
		
		model.addAttribute("list", boardService.boardList());
		
		return "board-list";
	}
	
	// localhost:8080/board/view?id=1
	@GetMapping("/board/view")
	public String boardView(Model model, Long id) {
		Optional<Board> boardOptional = boardService.boardView(id);
		
		if (boardService.boardView(id).isPresent()) {
			model.addAttribute("board", boardOptional.get());
			return "board-view";
		} else {
			return "board-view-error";
		}
		
	}
	
	@GetMapping("/board/delete")
	public String boardDelete(Long id) {
		
		boardService.boardDelete(id);
		
		return "redirect:/board/list";
	}
}
