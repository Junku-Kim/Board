package com.jk.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
/*
 * 홈 Controller입니다.
 */
public class HomeController {

	@GetMapping("/")
    public String home() {

		return "redirect:/board/list";
    }
}
