package com.jk.board.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name = "COMMENTS")
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_ID_SEQ_GENERATOR")
	@Column(name = "COMMENT_ID")
	private Long id;
	
	@Column(name = "COMMENTS", nullable = false, columnDefinition = "CLOB")
	private String comment;
	
	@Column(nullable = false)
	private String writer;
	
	private boolean isDeleted;
	
	public boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	@Column(nullable = false)
	private LocalDateTime createdDate = LocalDateTime.now();
	
	private LocalDateTime modifiedDate;
	
	@ManyToOne
	@JoinColumn(name = "BOARD_ID", nullable = false)
	private Board board;

	public void setBoard(Board board) {
		this.board = board;
	}
	
	@Builder
	public Comment(String comment, String writer, boolean isDeleted) {
		this.comment = comment;
		this.writer = writer;
		this.isDeleted = isDeleted;
	}
	
	public void update(String comment, String writer) {
		this.comment = comment;
		this.writer = writer;
		this.modifiedDate = LocalDateTime.now();
	}
	
	public void delete() {
		this.isDeleted = true;
	}
}
