package com.jk.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jk.board.dto.BoardRequest;
import com.jk.board.dto.BoardResponse;
import com.jk.board.paging.BoardCommonParams;
import com.jk.board.service.BoardService;

@RequestMapping("/api")
@RestController
public class BoardApiController {

	private final BoardService boardService;

	public BoardApiController(final BoardService boardService) {
		this.boardService = boardService;
	}

	/*
	 * 게시글 작성 메서드
	 * 
	 * Description:
	 *  MulitpartFile가 존재할 때는 @RequestBody를 통해 매개변수로 값을 받을 수 없습니다.
	 *  그러므로 @RequestParam과 @RequestPart를 통해 각각 받아야합니다.
	 *  - @RequestParam: title, content, writer와 같은 String 타입의 값을 받습니다.
	 *  - @RequestPart: multipartFiles와 같은 MultipartFile과 관련된 값들을 받습니다.
	 */
	@PostMapping("/boards")
	public Long wirteBoard(@RequestParam("title") final String title,
						   @RequestParam("content") final String content,
						   @RequestParam("writer") final String writer,
						   @RequestPart("multipartFiles") final List<MultipartFile> multipartFiles) throws Exception {
		
		BoardRequest boardRequest = BoardRequest.builder()
												.title(title)
												.content(content)
												.writer(writer)
												.isDeleted(false)
												.multipartFiles(multipartFiles)
												.build();
		
		return boardService.writeBoard(boardRequest);
	}

	/*
	 * 게시글 수정 메서드
	 */
	@PatchMapping("/boards/{id}")
	public Long updateBoard(@PathVariable final Long id,
							@RequestParam("title") final String title,
							@RequestParam("content") final String content,
							@RequestParam("writer") final String writer,
							@RequestPart("multipartFiles") final List<MultipartFile> multipartFiles) throws Exception {
		
		BoardRequest boardRequest = BoardRequest.builder()
				.title(title)
				.content(content)
				.writer(writer)
				.isDeleted(false)
				.multipartFiles(multipartFiles)
				.build();
		
		return boardService.updateBoard(id, boardRequest);
	}
	
	/*
	 * 게시글 삭제 메서드
	 */
	@DeleteMapping("/boards/{id}")
	public Long deleteBoard(@PathVariable final Long id) {
		return boardService.deleteBoard(id);
	}
	
	/*
	 * 게시글 리스트 조회 메서드
	 * 
	 * Description:
	 *  MyBatis로 개발한 메서드입니다. (추후 JPA로 마이그레이션 예정)
	 *  페이지 조건을 위한 매개변수를 나타내는 BoardCommonParams와 
	 *  그 조건에 맞게 조회된 리스트를 반환하기 위해 Map 자료형으로 반환되었습니다.
	 */
	@GetMapping("/boards")
	public Map<String, Object> findBoards(final BoardCommonParams params) {
		return boardService.findAllBoards(params);
	}

	/*
	 * 게시글 상세정보 조회 메서드
	 */
	@GetMapping("/boards/{id}")
	public BoardResponse findBoardById(@PathVariable final Long id) {
		return boardService.findBoardById(id);
	}
}
