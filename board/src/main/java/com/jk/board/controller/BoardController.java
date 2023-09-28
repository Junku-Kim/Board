package com.jk.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping("/layout")
	public String layouy() {
		return "layout";
	}
}
