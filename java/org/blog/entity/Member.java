package org.blog.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.blog.models.member.MemberType;

import javax.persistence.*;
import lombok.*;


/**
 * 
 참고 사이트
 https://github.com/yonggyo1125/curriculum300H/blob/main/6.Spring%20%26%20Spring%20Boot(75%EC%8B%9C%EA%B0%84)/17~20%EC%9D%BC%EC%B0%A8(21h)%20-%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/Spring%20Data%20JPA/2.%20Entity%20%EC%84%A4%EA%B3%84%ED%95%98%EA%B8%B0.md
 *
 */

@Entity
/* @Table(name="User") */
@Getter @Setter @ToString
public class Member {
	@Id @GeneratedValue	// 자동 증감번호 추가
	private Long memNo; 	// 회원번호
	
	@Column(length=40, unique=true, nullable=false)	// nullable=필수항목
	private String memId;  	// 회원 아이디
	
	@Column(length=65, nullable=false)
	private String memPw; 	// 회원 비밀번호
	
	@Column(length=40, nullable=false)
	private String memNm; 	// 회원명
	
	private String email; 	// 이메일
	
	@Column(length=11)
	private String mobile; 	// 전화번호
	
	// 회원인지 관리자인지 구분
	// 관리자 ADMIN, 일반회원 MEMBER
	@Enumerated(EnumType.STRING)
	@Column(length=40, nullable=false)
	private MemberType type = MemberType.MEMBER;
	
	
	@OneToMany(mappedBy="member", fetch=FetchType.LAZY)
	private List<BoardData> boardDatas;
}
