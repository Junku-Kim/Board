package com.jk.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jk.board.dto.BoardFileOriginalName;
import com.jk.board.repository.CustomBoardFileRepository;
import com.jk.board.service.BoardFileService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class BoardFileApiController {

	private final BoardFileService boardFileService;
	
	private final CustomBoardFileRepository customBoardFileRepository;
	
	/*
	 * 파일 원본 이름 리스트 조회 메서드
	 * 
	 * Description:
	 *  게시글 수정 시 파일 이름만을 나타내기 위한 메서드입니다.
	 *  파일의 원본 이름만 필요하기 때문에 BoardFile의 id와 원본 이름만 가진 DTO를 사용합니다.
	 */
	@GetMapping("/boards/{boardId}/files")
	public List<BoardFileOriginalName> findAllBoardFileOriginalNames(@PathVariable final Long boardId) {
		return customBoardFileRepository.selectBoardFileOriginalName(boardId);
	}
	
	/*
	 * 파일 삭제 메서드
	 */
	@DeleteMapping("/boards/{boardId}/files/{id}")
	public Long deleteBoardFile(@PathVariable final Long id) {
		return boardFileService.deleteFile(id);
	}
}
