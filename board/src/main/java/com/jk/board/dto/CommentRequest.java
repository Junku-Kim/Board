package com.jk.board.dto;

import com.jk.board.entity.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {

	private String comment;
	private String writer;
	private boolean isDeleted;
	
	public Comment toEntity() {
		return Comment.builder()
					  .comment(comment)
					  .writer(writer)
					  .isDeleted(isDeleted)
					  .build();
	}
	
	public boolean getIsDeleted() {
		return this.isDeleted;
	}
}
