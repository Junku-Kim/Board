package com.jk.board.dto;

import com.jk.board.entity.Board;
import com.jk.board.entity.BoardFile;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFileRequest {

	private Long id;
	private String originalName;
	private String savedName;
	private String uploadDir;
	private String extension;
	private long size;
	private String contentType;
	private Board board;

	@Builder
	public BoardFileRequest(Long id, String originalName, String savedName, String uploadDir, String extension, long size,
			String contentType, Board board) {
		this.id = id;
		this.originalName = originalName;
		this.savedName = savedName;
		this.uploadDir = uploadDir;
		this.extension = extension;
		this.size = size;
		this.contentType = contentType;
		this.board = board;
	}
	
	public BoardFile toEntity() {
		return BoardFile.builder()
				.originalName(originalName)
				.savedName(savedName)
				.uploadDir(uploadDir)
				.extension(extension)
				.size(size)
				.contentType(contentType)
				.board(board)
				.build();
	}
}
