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
// @CreatedDate를 사용하기 위한 어노테이션
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(
		name = "BOARD_FILE_ID_SEQ_GENERATOR",
		sequenceName = "BOARD_FILE_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
		)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BoardFile {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_FILE_ID_SEQ_GENERATOR")
	@Column(name = "BOARD_FILE_ID")
	private Long id;
	
	@Column(nullable = false)
	private String originalName; // 파일의 원본 이름
	
	@Column(nullable = false)
	private String savedName; // 파일이 저장될 때의 이름
	
	@Column(nullable = false)
	private String uploadDir; // 업로드 경로
	
	@Column(nullable = false)
	private String extension; // 파일의 확장자
	
	// oracle에 size 예약어가 존재하기 때문에 변경
	@Column(name = "FILE_SIZE", nullable = false)
	private long size;

	@Column(nullable = false)
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

	@Builder
	public BoardFile(String originalName, String savedName, String uploadDir, String extension, long size,
			String contentType, boolean isDeleted, Board board) {
		this.originalName = originalName;
		this.savedName = savedName;
		this.uploadDir = uploadDir;
		this.extension = extension;
		this.size = size;
		this.contentType = contentType;
		this.isDeleted = isDeleted;
		this.board = board;
	}
	
	public void delete() {
		this.isDeleted = true;
		this.deletedDate = LocalDateTime.now();
	}
}
