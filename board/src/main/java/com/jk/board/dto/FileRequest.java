package com.jk.board.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileRequest {

	private Long id;
	private Long boardId;
	private String originalName;
	private String savedName;
	private long size;
	
	@Builder
	public FileRequest(String originalName, String savedName, long size) {
		this.originalName = originalName;
		this.savedName = savedName;
		this.size = size;
	}
	
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
}
