package org.blog.controllers.members;

import javax.validation.Valid;

import org.blog.service.members.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/join")
public class JoinController {
	
	@Autowired
	private JoinService service;
	
	@Autowired
	private JoinValidator validator;
	
	@GetMapping
	public String form(Model model) {
		
		JoinRequest joinRequest = new JoinRequest();
		model.addAttribute("joinRequest", joinRequest);
		
		return "member/_form";
	}
	
	@PostMapping
	public String process(@Valid JoinRequest joinRequest, Errors errors) {
		
		validator.validate(joinRequest, errors);
		
		if(errors.hasErrors()) {
			return "member/_form";
		}
		
		service.process(joinRequest);
		
		return "redirect:/member/login";
	}
}
