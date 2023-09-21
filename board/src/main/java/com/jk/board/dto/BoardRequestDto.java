package com.jk.board.dto;

import com.jk.board.entity.Board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

	private String title;
	private String content;
	private String writer;
	
	public Board toEntity() {
		return Board.builder()
					.title(title)
					.content(content)
					.writer(writer)
					.hits(0)
					.build();
	}
}
