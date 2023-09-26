package com.jk.board.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@SequenceGenerator(
		name = "BOARD_ID_SEQ_GENERATOR",
		sequenceName = "BOARD_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
		)
/*
 * 접근권한을 protected로 하는 이유
 * JPA에서는 프록시를 생성하기 위해서 기본 생성자를 반드시 하나 생성해야한다.
 * 하지만, public으로 기본 생성자를 생성할 시 객체 안정성이 떨어진다.(원하지 않는 객체 생성)
 * 그렇다고 private으로 하면 JPA가 프록시를 만들 때 접근하지 못해 객체를 생성할 수 없다.
 * 그러므로 스펙에서는 기본 생성사 접근을 protected로 권장한다.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_ID_SEQ_GENERATOR")
	@Column(name = "BOARD_ID")
	private Long id;
	
	private String title;
	
	@Column(columnDefinition = "CLOB")
	private String content;
	
	private String writer;
	
	private int hits;
	
	private boolean delete;
	
	private LocalDateTime createdDate = LocalDateTime.now();
	
	private LocalDateTime modifiedDate;

	@Builder
	public Board(String title, String content, String writer, int hits, boolean delete) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.hits = hits;
		this.delete = delete;
	}
	
	public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.modifiedDate = LocalDateTime.now();
    }
}
