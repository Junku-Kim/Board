package com.jk.board.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(
		name = "MEMBER_ID_SEQ_GENERATOR",
		sequenceName = "MEMBER_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
		)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_ID_SEQ_GENERATOR")
	@Column(name = "MEMBER_ID")
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String memberName;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	private boolean isDeleted;
	
	public boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime createdDate;

	@Builder
	public Member(String memberName, String password, String email, boolean isDeleted) {
		this.memberName = memberName;
		this.password = password;
		this.email = email;
		this.isDeleted = isDeleted;
	}
	
}
