package com.jk.board.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@SequenceGenerator(
		name = "COMMENT_ID_SEQ_GENERATOR",
		sequenceName = "COMMENT_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
		)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_ID_SEQ_GENERATOR")
	@Column(name = "COMMENT_ID")
	private Long id;
	
	@Column(nullable = false, columnDefinition = "CLOB")
	private String comment;
	
	@Column(nullable = false)
	private String writer;
	
	@Column(nullable = false)
	private boolean isDeleted;
	
	@Column(nullable = false)
	private LocalDateTime createdDate = LocalDateTime.now();
	
	private LocalDateTime modifiedDate;
	
	@ManyToOne
	@JoinColumn(name = "BOARD_ID")
	private Board board;

	@Builder
	public Comment(String comment, String writer, boolean isDeleted, Board board) {
		this.comment = comment;
		this.writer = writer;
		this.isDeleted = isDeleted;
		this.board = board;
	}
	
	public void update(String comment, String writer, Board board) {
		this.comment = comment;
		this.writer = writer;
		this.modifiedDate = LocalDateTime.now();
		this.board = board;
	}
	
	public void delete() {
		this.isDeleted = true;
	}
}
