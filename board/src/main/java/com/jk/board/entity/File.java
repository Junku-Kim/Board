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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@SequenceGenerator(
		name = "BOARD_FILE_ID_SEQ_GENERATOR",
		sequenceName = "BOARD_FILE_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
		)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "BOARD_FILE")
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_FILE_ID_SEQ_GENERATOR")
	@Column(name = "BOARD_FILE_ID")
	private Long id;
	
	@Column(nullable = false)
	private String originalName;
	
	@Column(nullable = false)
	private String savedName;
	
	@Column(name = "FILE_SIZE")
	private long size;
	
	private boolean isDeleted;
	
	public boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	@Column(nullable = false)
	private LocalDateTime createdDate = LocalDateTime.now();
	
	private LocalDateTime deletedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BOARD_ID", nullable = false)
	private Board board;
	
	public void setBoard(Board board) {
		this.board = board;
	}
}
