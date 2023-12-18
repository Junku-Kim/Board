package com.jk.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.board.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Optional<Member> findByMemberName(String MemberName);
	
	Optional<Member> findByEmail(String email);
}
