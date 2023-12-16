package com.jk.board.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.board.dto.MemberRequest;
import com.jk.board.entity.Member;
import com.jk.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public Long join(final MemberRequest memberRequest) {
		Member member = Member.builder()
							  .memberName(memberRequest.getMemberName())
							  .password(passwordEncoder.encode(memberRequest.getPassword()))
							  .email(memberRequest.getEmail())
							  .isDeleted(memberRequest.getIsDeleted())
							  .build();
		
		return memberRepository.save(member).getId();
	}
}
