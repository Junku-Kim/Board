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
import com.jk.board.paging.BoardCommonParams;
import com.jk.board.paging.Pagination;
import com.jk.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	
	private final BoardFileService boardFileService;
	
	/*
	 * 게시글 생성 메서드
	 */
	public Long writeBoard(final BoardRequest boardRequest) throws Exception {
		Board board = boardRepository.save(boardRequest.toEntity());
		Long id = board.getId();
		
		boardFileService.saveFiles(id, boardRequest);
		
		return id;
	}
	
	/*
	 * 게시글 수정 메서드
	 */
	public Long updateBoard(final Long id, final BoardRequest boardRequest) throws Exception {
		Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		board.update(boardRequest.getTitle(), boardRequest.getContent(), boardRequest.getWriter());
		
		boardFileService.saveFiles(id, boardRequest);
		
		return id;
	}
	
	/*
	 * 게시글 삭제 메서드
	 */
	public Long deleteBoard(final Long id) {
		Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		board.delete();
		
		return id;
	}
	
	/*
	 * 게시글 리스트 조회 메서드(페이지네이션)
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> findAllBoards(final BoardCommonParams params) {
		Long count = 0L;
		// 검색어가 있을 때
		if (params.getKeywordForSearch() != "") {
			if (params.getSearchType().equals("")) { // 전체 선택
				count = boardRepository.countBoardByAllSearchType(params.getKeywordForSearch());
			} else { // 단일 검색
				count = boardRepository.countBoardBySearchType(params.getSearchType(), params.getKeywordForSearch());
			}
		} else { // 검색어가 없을 때
			count = boardRepository.countBoardDefault();
		}
		
		if (count < 1L) {
			return Collections.emptyMap();
		}
		
		Pagination pagination = new Pagination(count.intValue(), params);
		params.setPagination(pagination);
		
		List<BoardResponse> list = Collections.emptyList();
		
		// 검색어가 있을 때
		if (params.getKeywordForSearch() != "") {
			if (params.getSearchType().equals("")) { // 전체 검색
				list = boardRepository.findPageBoardsByAllSearchType(params);
			} else { // 단일 검색
				list = boardRepository.findPageBoardsBySearchType(params);
			}
		} else { // 검색어가 없을 때
			list = boardRepository.findPageBoardsDefault(params);
		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("params", params);
		response.put("list", list);
		return response;
	}
	
	/*
	 * 게시글 상세정보 조회 메서드
	 */
	@Transactional(readOnly = true)
	public BoardResponse findBoardById(final Long id) {
		Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		board.increaseHits();
		
		return new BoardResponse(board);
	}
}
