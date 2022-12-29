package org.blog.controllers.members;

import org.blog.entity.Member;
import org.blog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class JoinValidator implements Validator {	// 회원가입 한정

	@Autowired
	private MemberRepository repository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return JoinRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		JoinRequest joinRequest = (JoinRequest)target;
		
		// errors - rejectValue(특정필드), reject
		
		/**
		 * 0. 아이디 중복 여부 체크
		 * 1. 비밀번호와 비밀번호 확인
		 * 2. 휴대전화번호 유입되면 형식 체크
		 */
		
		
		String memId = joinRequest.getMemId();
		if(memId != null && !memId.isBlank()) {
			Member member = repository.findByMemId(memId);
			if(member != null) {	// 중복 아이디!
				errors.rejectValue("memId", "duplicate.memId", "이미 가입된 아이디 입니다.");
			}
		}
		
		
		String memPw = joinRequest.getMemPw();
		String memPwRe = joinRequest.getMemPwRe();
		if(!memPw.isBlank() && !memPwRe.isBlank() && !memPw.equals(memPwRe)) {
			errors.rejectValue("memPwRe", "incorrect.password", "비밀번호가 일치하지 않습니다.");
		}
		
		
		String mobile = joinRequest.getMobile();
		if(mobile != null && !mobile.isBlank()) {	// 휴대전화번호는 필수x, 값이 있는 경우만 체크
			// 01000000000 / 010 0000 0000 / 010-0000-0000 / 010_0000  \d -> [0-0], \D -> [^0-9]
			mobile = mobile.replaceAll("\\D","");	// 숫자가 아닌 문자를 제거 -> 숫자
			String pattern = "01[06]\\d{3,4}\\d{4}";
			
			if(!mobile.matches(pattern)) {	// 휴대전화 번호 형식x
				errors.rejectValue("mobile", "incorrect.mobile", "휴대전화번호 형식이 아닙니다.");
			}
				
			joinRequest.setMobile(mobile);
		}
	}
	 

}
