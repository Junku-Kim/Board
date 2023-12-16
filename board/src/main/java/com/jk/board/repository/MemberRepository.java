package com.jk.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.board.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
