package com.jk.board.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jk.board.dto.MemberRequest;
import com.jk.board.entity.Member;
import com.jk.board.repository.MemberRepository;

@Transactional
@SpringBootTest
public class MemberServiceIntegrationTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	MemberService memberService;
	
	List<MemberRequest> memberRequests;
	
	@BeforeEach
	void beforeEach() {
		memberRequests
		= List.of(
				// 0. 정상 값
				MemberRequest
				.builder()
				.memberName("김준구")
				.password("jk0320")
				.passwordForCheck("jk0320")
				.email("junku0320@gmail.com")
				.isDeleted(false)
				.build(),
				
				// 1. 이름 중복
				MemberRequest
				.builder()
				.memberName("김준구")
				.password("jk0320")
				.passwordForCheck("jk0320")
				.email("kimjunku0320@gmail.com")
				.isDeleted(false)
				.build(), 
				
				// 2. 비밀번호가 서로 일치하지 않음
				MemberRequest
				.builder()
				.memberName("김준팔")
				.password("jk0320")
				.passwordForCheck("jk0321")
				.email("kimjunku0320@gmail.com")
				.isDeleted(false)
				.build(),
				
				// 3. 이메일 중복
				MemberRequest
				.builder()
				.memberName("김준팔")
				.password("jk0320")
				.passwordForCheck("jk0320")
				.email("junku0320@gmail.com")
				.isDeleted(false)
				.build()
				
				);
	}
	
	@AfterEach
	void afterEach() {
		memberRepository.deleteAll();
	}
	
	@Test
	void 회원_등록() {
		// 정상 값
		MemberRequest memberRequest = memberRequests.get(0);
		
		Long result = memberService.join(memberRequest);
		
		Optional<Member> memberOptional =  memberRepository.findById(result);
		
		assertThat(result).isEqualTo(memberOptional.get().getId());
	}
	
	@Test
	void 회원_이름_중복() {
		// 정상 값
		MemberRequest memberRequest1 = memberRequests.get(0);
		
		// 중복 이름 값
		MemberRequest memberRequest2 = memberRequests.get(1);
		
		memberService.join(memberRequest1);
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> memberService.join(memberRequest2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 이름입니다.");
	}
	
	@Test
	void 회원_비밀번호_확인_불일치() {
		// 정상 값
		MemberRequest memberRequest1 = memberRequests.get(0);
		
		// 비밀번호와 비밀번호 확인이 서로 불일치하는 값
		MemberRequest memberRequest2 = memberRequests.get(2);
		
		memberService.join(memberRequest1);
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> memberService.join(memberRequest2));
		
		assertThat(e.getMessage()).isEqualTo("입력한 두 비밀번호가 다릅니다.");
	}
	
	@Test
	void 회원_이메일_중복() {
		// 정상 값
		MemberRequest memberRequest1 = memberRequests.get(0);
		
		// 중복 이메 값
		MemberRequest memberRequest2 = memberRequests.get(3);
		
		memberService.join(memberRequest1);
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> memberService.join(memberRequest2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 이메일입니다.");
	}
}
