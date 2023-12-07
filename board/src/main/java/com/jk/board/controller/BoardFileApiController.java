package com.jk.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jk.board.dto.BoardFileOriginalName;
import com.jk.board.repository.CustomBoardRepository;
import com.jk.board.service.BoardFileService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class BoardFileApiController {

	private final BoardFileService boardFileService;
	
	private final CustomBoardRepository customBoardRepository;
	
	/*
	 * 파일 원본 이름 리스트 조회
	 */
	@GetMapping("/boards/{boardId}/files")
	public List<BoardFileOriginalName> findAllBoardFileOriginalNames(@PathVariable final Long boardId) {
		return customBoardRepository.selectBoardFileOriginalName(boardId);
	}
	/*
	 * 파일 삭제
	 */
	@DeleteMapping("/boards/{boardId}/files/{id}")
	public Long deleteBoardFile(@PathVariable final Long id) {
		return boardFileService.deleteFile(id);
	}
}
