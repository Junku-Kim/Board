package com.jk.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
/*
 * 게시글 수정 시 원본 이름만을 보여주기 위한 BoardFile DTO입니다.
 */
public class BoardFileOriginalName {

	private Long id;
	private String originalName;

	@Builder
	public BoardFileOriginalName(Long id, String originalName) {
		this.id = id;
		this.originalName = originalName;
	}
	
	
}
