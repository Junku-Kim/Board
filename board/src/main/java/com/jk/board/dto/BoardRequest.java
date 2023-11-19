package com.jk.board.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
	
	public boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	private List<MultipartFile> multipartFiles;
	
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
