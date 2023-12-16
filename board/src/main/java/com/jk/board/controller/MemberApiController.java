package com.jk.board.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jk.board.dto.MemberRequest;
import com.jk.board.service.MemberService;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberApiController {

	private final MemberService memberService;
	
	public MemberApiController(final MemberService service) {
		this.memberService = service;
	}
	
	/*
	 * 회원 저장 메서드
	 */
	@PostMapping("/members")
	public Long create(@RequestBody final MemberRequest memberRequest) {
		return memberService.join(memberRequest);
	}
}
