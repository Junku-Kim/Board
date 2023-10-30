package com.jk.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<CommentResponse> writeComment(@PathVariable final Long boardId, @RequestBody final CommentRequest commentRequest) {
		Long commentId = commentService.writeComment(boardId ,commentRequest);
		Optional<CommentResponse> commentResponseOptional = commentService.findCommentById(commentId);
		
		if (commentResponseOptional.isPresent()) {
			return ResponseEntity.ok(commentResponseOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * 댓글 리스트 조회
	 */
	@GetMapping("/boards/{boardId}/comments")
	public List<CommentResponse> findAllComments(@PathVariable final Long boardId) {
		return commentService.findAllComments(boardId);
	}
}
