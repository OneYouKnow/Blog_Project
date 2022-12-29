package org.blog.service.members;

import javax.servlet.http.HttpSession;

import org.blog.controllers.members.LoginRequest;
import org.blog.entity.Member;
import org.blog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import org.mindrot.bcrypt.BCrypt;

@Service
public class LoginService {
	
	@Autowired
	private MemberRepository repo;
	
	@Autowired
	private HttpSession session;
	
	public void process(LoginRequest loginRequest, Errors errors) {
		
		if(errors.hasErrors()) {
			return;
		}
		
		try {
			
			String memId = loginRequest.getMemId();
			String memPw = loginRequest.getMemPw();
			Member member = repo.findByMemId(memId);
			if (member == null) {
				throw new RuntimeException("등록되지 않은 회원입니다.");
			}
			
			boolean isMatch = BCrypt.checkpw(memPw, member.getMemPw());
			if (!isMatch) {
				throw new RuntimeException("비밀번호가 일치하지 않습니다.");
			}
			
			
			// 회원이 존재하고 비밀번호가 일치하면 로그인 처리 
			session.setAttribute("member", member);
		} catch (RuntimeException e) {
			errors.reject("loginError", e.getMessage());
		}
	}
}
