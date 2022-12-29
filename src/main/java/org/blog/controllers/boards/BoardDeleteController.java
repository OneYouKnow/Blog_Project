package org.blog.controllers.boards;

import org.blog.service.boards.BoardDataDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardDeleteController {
	
	@Autowired
	private BoardDataDeleteService service;
	
	@RequestMapping("/board/delete/{id}")
	public String process(@PathVariable Long id, Model model) {
		
		try {
			
			service.process(id);
			
		} catch (RuntimeException e) {
			
			model.addAttribute("scripts", "alert('" + e.getMessage() + "');history.back()");
			return "common/scripts";
		}
		
		
		return "redirect:/board/list";
	}
}
