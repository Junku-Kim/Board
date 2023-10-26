package com.jk.board.dto;

import com.jk.board.entity.Board;
import com.jk.board.entity.Comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {

	private String comment;
	private String writer;
	private boolean isDeleted;
	private Board board;
	
	public Comment toEntity() {
		return Comment.builder()
					  .comment(comment)
					  .writer(writer)
					  .isDeleted(isDeleted)
					  .board(board)
					  .build();
	}
	
	public boolean getIsDeleted() {
		return this.isDeleted;
	}
}
