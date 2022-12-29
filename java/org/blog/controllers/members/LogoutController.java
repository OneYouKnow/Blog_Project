package org.blog.controllers.members;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	
	@RequestMapping("/member/logout")
	public String process(HttpSession session) {
		
		// 로그아웃 처리 
		session.invalidate();
		
		return "redirect:/member/login";	// 로그아웃 시 로그인 페이지로 이동
	}
}
