package com.jk.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jk.board.repository.CustomBoardFileRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardViewController {
	
	private final CustomBoardFileRepository customBoardFileRepository;

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
		if (id != null) {
			model.addAttribute("boardFileOriginalName", customBoardFileRepository.selectBoardFileOriginalName(id));
		}
		return "board/write";
	}
	
	/*
	 * 게시글 상세 페이지
	 */
	@GetMapping("/view/{id}")
	public String viewBoard(@PathVariable final Long id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("boardFile", customBoardFileRepository.selectBoardFileDetail(id));
		
		return "board/view";
	}
}
