package org.blog.controllers.members;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter @Setter @ToString
public class LoginRequest {
	@NotBlank(message="아이디를 입력하세요.")
	private String memId;
	
	@NotBlank(message="비밀번호를 입력하세요.")
	private String memPw;
	private boolean savedMemId;
}
