package com.jk.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jk.board.dto.CommentRequest;
import com.jk.board.dto.CommentResponse;
import com.jk.board.service.CommentService;

@RequestMapping("/api")
@RestController
public class CommentApiController {

	private final CommentService commentService;
	
	public CommentApiController(final CommentService commentService) {
		this.commentService = commentService;
	}
	
	/*
	 * 댓글 생성
	 */
	@PostMapping("/boards/{boardId}/comments")
	public Long writeComment(@PathVariable final Long boardId, @RequestBody final CommentRequest commentRequest) {
		return commentService.writeComment(boardId ,commentRequest);
	}
	
	/*
	 * 댓글 리스트 조회
	 */
	@GetMapping("/boards/{boardId}/comments")
	public List<CommentResponse> findAllComments(@PathVariable final Long boardId) {
		return commentService.findAllComments(boardId);
	}
	
	/*
	 * 댓글 수정
	 */
	@PatchMapping("/boards/{boardId}/comments/{id}")
	public Long updateComment(@PathVariable final Long id, @RequestBody final CommentRequest commentRequest) {
		return commentService.updateComment(id, commentRequest);
	}
	/*
	 * 댓글 삭제
	 */
	@DeleteMapping("/boards/{boardId}/comments/{id}")
	public Long deleteComment(@PathVariable final Long id) {
		return commentService.deleteComment(id);
	}
}
