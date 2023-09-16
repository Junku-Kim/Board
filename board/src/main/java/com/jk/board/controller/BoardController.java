package com.jk.board.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

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
	public String boardWritePro(Board board, Model model, MultipartFile file) throws IllegalStateException, IOException {
		boardService.boardWrite(board, file);
		
		model.addAttribute("message", "게시글 작성이 완료되었습니다.");
		model.addAttribute("searchUrl", "/board/list");
		
		return "message";
	}
	
	@GetMapping("/board/list")
	public String boardList(Model model,
							@PageableDefault(page = 0, size = 10, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
							String searchKeyword) {
		
		Page<Board> boardPage = null;
		
		if (searchKeyword == null) {
			boardPage = boardService.boardList(pageable);
		} else {
			boardPage = boardService.boardSearchList(searchKeyword, pageable);
		}
		
		
		// int nowPage = pageable.getPageNumber() + 1;
		// 상단의 방법도 가능하지만 통일성을 위해서 하단의 방법으로, 그리고 0부터 시작하기 때문에 1을 더해준다.
		int nowPage = boardPage.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, boardPage.getTotalPages());
		
		model.addAttribute("boardPage", boardPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "board-list";
	}
	
	// localhost:8080/board/view/1
	@GetMapping("/board/view/{id}")
	public String boardView(@PathVariable Long id, Model model) {
		Optional<Board> boardOptional = boardService.boardView(id);
		
		if (boardService.boardView(id).isPresent()) {
			model.addAttribute("board", boardOptional.get());
			return "board-view";
		} else {
			return "board-view-error";
		}
		
	}
	
	@GetMapping("/board/delete/{id}")
	public String boardDelete(@PathVariable Long id, Model model) {
		boardService.boardDelete(id);
		
		model.addAttribute("message", "게시글 삭제가 완료되었습니다.");
		model.addAttribute("searchUrl", "/board/list");
		
		return "message";
	}
	
	@GetMapping("/board/modify/{id}")
	public String boardModify(@PathVariable("id") Long id, Model model) {
		Optional<Board> boardOptional = boardService.boardView(id);
		
		model.addAttribute("board", boardOptional.get());
		
		return "board-modify";
	}
	
	@PostMapping("/board/update/{id}")
	public String boardUpdate(@PathVariable("id") Long id, Board board, Model model, MultipartFile file) throws IllegalStateException, IOException {
		
		Board newBoard = board.withTitleAndContent(board.getTitle(), board.getContent());
		
		boardService.boardWrite(newBoard, file);
		
		model.addAttribute("message", "게시글 수정이 완료되었습니다.");
		model.addAttribute("searchUrl", "/board/list");
		
		return "message";
	}
}
