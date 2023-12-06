package com.jk.board.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.board.dto.BoardRequest;
import com.jk.board.dto.BoardResponse;
import com.jk.board.entity.Board;
import com.jk.board.exception.CustomException;
import com.jk.board.exception.ErrorCode;
import com.jk.board.mapper.BoardMapper;
import com.jk.board.paging.BoardCommonParams;
import com.jk.board.paging.Pagination;
import com.jk.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	private final BoardMapper boardMapper;
	
	private final BoardFileService boardFileService;
	
	/*
	 * 게시글 생성
	 */
	public Long writeBoard(final BoardRequest boardRequest) throws Exception {
		Board board = boardRepository.save(boardRequest.toEntity());
		Long id = board.getId();
		
		boardFileService.saveFiles(id, boardRequest);
		
		return id;
	}
	
	/*
	 * 게시글 수정
	 */
	public Long updateBoard(final Long id, final BoardRequest boardRequest) throws Exception {
		Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		board.update(boardRequest.getTitle(), boardRequest.getContent(), boardRequest.getWriter());
		
		boardFileService.saveFiles(id, boardRequest);
		
		return id;
	}
	
	/*
	 * 게시글 삭제
	 */
	public Long deleteBoard(final Long id) {
		Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		board.delete();
		
		return id;
	}
	
	/*
	 * 게시글 리스트 조회 (페이지네이션)
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> findAllBoards(final BoardCommonParams params) {
		int count = boardMapper.countBoard(params);

		if (count < 1) {
			return Collections.emptyMap();
		}
		
		Pagination pagination = new Pagination(count, params);
		params.setPagination(pagination);
		
		List<BoardResponse> list = boardMapper.findAllBoards(params);
		
		Map<String, Object> response = new HashMap<>();
		response.put("params", params);
		response.put("list", list);
		return response;
	}
	
	/*
	 * 게시글 상세정보 조회
	 */
	@Transactional(readOnly = true)
	public BoardResponse findBoardById(final Long id) {
		Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		board.increaseHits();
		
		return new BoardResponse(board);
	}
}
