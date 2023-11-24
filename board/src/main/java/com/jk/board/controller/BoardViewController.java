package com.jk.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jk.board.repository.CustomBoardRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/board")
@Controller
public class BoardViewController {
	
	private final CustomBoardRepository customBoardRepository;

	/*
	 * 게시글 리스트 페이지
	 */
	@GetMapping("/list")
	public String listBoards() {
		return "board/list";
	}
	
	/*
	 * 게시글 작성 페이지
	 */
	@GetMapping("/write")
	public String writeBoard(@RequestParam(required = false) final Long id, Model model) {
		model.addAttribute("id", id);
		
		return "board/write";
	}
	
	/*
	 * 게시글 상세 페이지
	 */
	@GetMapping("/view/{id}")
	public String viewBoard(@PathVariable final Long id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("boardFile", customBoardRepository.selectBoardFileDetail(id));
		
		return "board/view";
	}
}
