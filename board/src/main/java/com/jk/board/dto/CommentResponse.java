package com.jk.board.dto;

import java.time.LocalDateTime;

import com.jk.board.entity.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponse {

	private Long id;
	private String comment;
	private String writer;
	private boolean isDeleted;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public CommentResponse(final Comment comment) {
		this.id = comment.getId();
		this.comment = comment.getComment();
		this.writer = comment.getWriter();
		this.isDeleted = comment.getIsDeleted();
		this.createdDate = comment.getCreatedDate();
		this.modifiedDate = comment.getModifiedDate();
	}
	
	public boolean getIsDeleted() {
		return this.isDeleted;
	}
}
