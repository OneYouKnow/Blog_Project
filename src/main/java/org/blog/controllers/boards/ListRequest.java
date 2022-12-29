package org.blog.controllers.boards;

import lombok.*;

@Getter @Setter @ToString
public class ListRequest {
	private Long page;
	private String sopt;	// 검색 옶션, 제목, 내용, 제목+내용, 작성자
	private String skey; 	// 검색어
}
