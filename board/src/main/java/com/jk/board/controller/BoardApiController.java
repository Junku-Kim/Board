package com.jk.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jk.board.exception.CustomException;
import com.jk.board.exception.ErrorCode;

@RequestMapping("/api")
@RestController
public class BoardApiController {

	@GetMapping("/test")
	public String test() {
		throw new CustomException(ErrorCode.NOT_FOUND);
	}
}
