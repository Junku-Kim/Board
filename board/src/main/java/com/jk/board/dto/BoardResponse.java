package com.jk.board.dto;

import java.time.LocalDateTime;

import com.jk.board.entity.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponse {

	private Long id;
	private String title;
    private String content;
    private String writer;
    private int hits;
    private boolean isDeleted;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    
    public BoardResponse(final Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.hits = board.getHits();
        this.isDeleted = board.getIsDeleted();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }
    
    public boolean getIsDeleted() {
    	return this.isDeleted;
    }
}
