package com.jk.board.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFileOriginalName {

	private String originalName;

	@Builder
	public BoardFileOriginalName(String originalName) {
		this.originalName = originalName;
	}
	
	
}
