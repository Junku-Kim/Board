package com.jk.board.dto;

import java.time.LocalDateTime;

import com.jk.board.entity.Board;
import com.jk.board.entity.Comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponse {

	private Long id;
	private String comment;
	private String writer;
	private boolean isDeleted;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private Board board;
	
	public CommentResponse(final Comment comment) {
		this.id = comment.getId();
		this.comment = comment.getComment();
		this.writer = comment.getWriter();
		this.isDeleted = comment.isDeleted();
		this.createdDate = comment.getCreatedDate();
		this.modifiedDate = comment.getModifiedDate();
		this.board = comment.getBoard();
	}
}
