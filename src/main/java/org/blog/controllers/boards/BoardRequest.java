package org.blog.controllers.boards;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter @Setter @ToString
public class BoardRequest {
	
	private Long id;
	
	@NotBlank(message="작성자를 입력하세요.")
	private String poster;
	
	@NotBlank(message="제목을 입력하세요.")
	private String subject;
	
	@NotBlank(message="내용을 입력하세요.")
	private String contents;
}
