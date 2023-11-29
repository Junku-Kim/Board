package com.jk.board.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFileOriginalName {

	private Long id;
	private String originalName;

	@Builder
	public BoardFileOriginalName(Long id, String originalName) {
		this.id = id;
		this.originalName = originalName;
	}
	
	
}
