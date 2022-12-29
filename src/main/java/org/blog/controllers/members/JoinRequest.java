package org.blog.controllers.members;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

@Getter @Setter @ToString
public class JoinRequest {
	@NotBlank(message="아이디를 입력하세요.")
	@Size(min=6, message="아이디는 6자리 이상 입력하세요.")
	private String memId;
	
	@NotBlank(message="비밀번호를 입력하세요.")
	@Size(min=8, message="비밀번호는 8자리 이상 입력하세요.")
	private String memPw;
	
	@NotBlank(message="비밀번호를 확인하세요.")
	private String memPwRe;
	
	@NotBlank(message="회원명을 입력하세요")
	private String memNm;
	
	@Email(message="이메일 형식이 아닙니다.")
	private String email;
	private String mobile;
}
