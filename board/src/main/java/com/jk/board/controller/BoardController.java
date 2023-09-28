package com.jk.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
public class BoardController {

	/*
	 * 게시글 리스트 페이지
	 */
	@GetMapping("/list")
	public String listBoards() {
		return "board/list";
	}
}
