package org.blog.controllers.boards;

import org.blog.entity.BoardData;
import org.blog.service.boards.BoardDataInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardViewController {
	
	@Autowired
	private BoardDataInfoService service;
	
	@GetMapping("/board/view/{id}")
	public String view(@PathVariable Long id, Model model) {
		
		try {
			BoardData data = service.get(id);
			System.out.println(data);
			
			model.addAttribute("boardData", data);
		} catch (RuntimeException e) {
			
			String message = e.getMessage();
			String scripts = "alert('" + message + "');history.back();";
			model.addAttribute("scripts", scripts);
			
			return "common/scripts";
		}
		
		return "board/view";
		
	}
}
