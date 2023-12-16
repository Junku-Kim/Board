package com.jk.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequest {

	private String memberName;
	private String password;
	private String email;
	private boolean isDeleted;
	
	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	@Builder
	public MemberRequest(String memberName, String password, String email, boolean isDeleted) {
		super();
		this.memberName = memberName;
		this.password = password;
		this.email = email;
		this.isDeleted = isDeleted;
	}
	
}
