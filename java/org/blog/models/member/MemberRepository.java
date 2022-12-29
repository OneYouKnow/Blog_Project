package org.blog.models.member;

import java.time.LocalDateTime;
import java.util.List;

import org.blog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

interface MemberRepository {
	// memId 조회
	Member findMemberByMemId(String memId);
	
	// 가입일자 기간
	List<Member> findByRegDtBetweenOrderByRegDtDesc(LocalDateTime sDate, LocalDateTime eDate);
	
	List<Member> findByMemNmContaining(String key);
	
	@Query("SELECT m FROM Member m WHERE m.memId LIKE %:keyword% OR m.memNm LIKE %:keyword%")	// * = m
	List<Member> findByKeyword(@Param("keyword") String keyword);
}
