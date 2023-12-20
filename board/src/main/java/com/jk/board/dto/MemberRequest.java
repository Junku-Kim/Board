package com.jk.board.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequest {

	@Size(min = 2, max = 20)
	@NotBlank(message = "회원 이름은 필수 항목입니다.")
	private String memberName;
	
	@Size(min = 6, max = 20)
	@NotBlank(message = "비밀번호는 필수 항목입니다.")
	private String password;
	
	@Size(min = 6, max = 20)
	@NotBlank(message = "비밀번호는 필수 항목입니다.")
	private String passwordForCheck;
	
	@NotBlank(message = "이메일은 필수 항목입니다.")
	@Email
	private String email;
	
	private boolean isDeleted;
	
	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	@Builder
	public MemberRequest(String memberName, String password, String passwordForCheck, String email, boolean isDeleted) {
		this.memberName = memberName;
		this.password = password;
		this.passwordForCheck = passwordForCheck;
		this.email = email;
		this.isDeleted = isDeleted;
	}
	
}
