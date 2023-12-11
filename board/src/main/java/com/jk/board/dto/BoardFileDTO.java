package com.jk.board.dto;

import com.jk.board.entity.Board;
import com.jk.board.entity.BoardFile;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
/*
 * 스프링부트를 사용할 때는 기본 생성자가 없어도  jackson-module-parameter-names을 통해 정상적으로 작동하지만
 * 종속되지 않게 기본 생성자를 추가했습니다.
 */
@NoArgsConstructor
public class BoardFileDTO {

	private Long id;
	private String originalName;
	private String savedName;
	private String uploadDir;
	private String extension;
	private long size;
	private String contentType;
	private Board board;

	@Builder
	public BoardFileDTO(Long id, String originalName, String savedName, String uploadDir, String extension, long size,
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
