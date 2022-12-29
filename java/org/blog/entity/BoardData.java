package org.blog.entity;

import java.time.LocalDateTime;	// 작성 날짜

import javax.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
public class BoardData {
	// 제목, 내용, 작성자, 작성한 회원 정보
	
	@Id @GeneratedValue		// 자동 증감번호
	private Long id;
	
	@Column(length=40, nullable=false)
	private String poster; 	// 작성자명
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="memNo")
	private Member member;	// 작성한 회원 정보
	
	@Column(nullable=false)
	private String subject;	// 게시글 제목
	
	@Lob // 롱 텍스트 형태
	@Column(nullable=false)
	private String contents;// 게시글 내용
	
	@Override
	public String toString() {
		return "BoardData [id=" + id + ", subject=" + subject + ", contents=" + contents + ", poster=" + poster + "]";
	}
}
