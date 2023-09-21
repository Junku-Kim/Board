package com.jk.board.dto;

import java.time.LocalDateTime;

import com.jk.board.entity.Board;

import lombok.Getter;

@Getter
public class BoardResponseDto {

	private Long id;
	private String title;
    private String content;
    private String writer;
    private int hits;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    
    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.hits = board.getHits();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }
}
