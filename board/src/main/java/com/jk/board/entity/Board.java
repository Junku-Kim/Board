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
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false, columnDefinition = "CLOB")
	private String content;
	
	@Column(nullable = false)
	private String writer;
	
	// 원시 타입의 자료형은 기본적으로 null이 불가능하기 때문에 @Column을 통해 null을 제어할 필요가 없다.
	private int hits; // 조회수
	
	// Soft Delete
	private boolean isDeleted;
	
	/*
	 * @Getter 어노테이션은 boolean 자료형을 가진 필드들은 isXXX로 getter를 만들어준다.
	 * 그리고 jackson은 JSON을 가지고 올 때 getter를 보고 키 값을 만들게 된다.
	 * 즉, isDeleted라는 키 값이 아닌 deleted라는 키값이 생성된다.
	 * 따로 getter를 추가해주거나 래퍼 클래스를 사용하면 된다.(래퍼 클래스일 경우 getXXX로 getter를 만들어준다.)
	 * 굳이 자료형을 바꾸기 보단 getter를 따로 추가해주는 편이 좋을 것 같아 getter 추가하기로 결정했다.
	 */
	public boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	@Column(nullable = false)
	private LocalDateTime createdDate = LocalDateTime.now();
	
	private LocalDateTime modifiedDate;
	
	@Builder
	public Board(String title, String content, String writer, int hits, boolean isDeleted) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.hits = hits;
		this.isDeleted = isDeleted;
	}
	
	public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.modifiedDate = LocalDateTime.now();
    }
	
	public void increaseHits() {
		this.hits++;
	}
	
	public void delete() {
		this.isDeleted = true;
	}
}
