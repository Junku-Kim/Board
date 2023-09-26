package com.jk.board.dto;

import com.jk.board.entity.Board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequest {

	private String title;
	private String content;
	private String writer;
	private boolean isDeleted;
	
	public Board toEntity() {
		return Board.builder()
					.title(title)
					.content(content)
					.writer(writer)
					.hits(0)
					.isDeleted(isDeleted)
					.build();
	}
}
