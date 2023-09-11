package com.jk.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping("/board/write")
	public String boardWriteForm() {
		
		return "boardwrite";
	}
}
