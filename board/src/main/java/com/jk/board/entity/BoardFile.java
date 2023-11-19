package com.jk.board.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
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
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(
		name = "BOARD_FILE_ID_SEQ_GENERATOR",
		sequenceName = "BOARD_FILE_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
		)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity()
public class BoardFile {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_FILE_ID_SEQ_GENERATOR")
	@Column(name = "BOARD_FILE_ID")
	private Long id;
	
	@Column(nullable = false)
	private String originalName;
	
	@Column(nullable = false)
	private String savedName;
	
	private String uploadDir;
	
	private String extension;
	
	@Column(name = "FILE_SIZE")
	private long size;

	private String contentType;
	
	private boolean isDeleted;
	
	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime createdDate;
	
	private LocalDateTime deletedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BOARD_ID", nullable = false)
	private Board board;
	
	public void setBoard(Board board) {
		this.board = board;
	}

	@Builder
	public BoardFile(String originalName, String savedName, String uploadDir, String extension, long size,
			String contentType, boolean isDeleted) {
		this.originalName = originalName;
		this.savedName = savedName;
		this.uploadDir = uploadDir;
		this.extension = extension;
		this.size = size;
		this.contentType = contentType;
		this.isDeleted = isDeleted;
	}
}
