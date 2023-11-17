package com.jk.board.dto;

import com.jk.board.entity.Board;
import com.jk.board.entity.File;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileRequest {

	private Long id;
	private String originalName;
	private String savedName;
	private String uploadDir;
	private String extension;
	private long size;
	private String contentType;
	private Board board;
	
	public void setBoard(Board board) {
		this.board = board;
	}

	@Builder
	public FileRequest(String originalName, String savedName, String uploadDir, String extension, long size,
			String contentType) {
		this.originalName = originalName;
		this.savedName = savedName;
		this.uploadDir = uploadDir;
		this.extension = extension;
		this.size = size;
		this.contentType = contentType;
	}
	
	public File toEntity() {
		return File.builder()
				.originalName(originalName)
				.savedName(savedName)
				.uploadDir(uploadDir)
				.extension(extension)
				.size(size)
				.contentType(contentType)
				.build();
	}
}
