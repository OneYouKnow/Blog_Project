package org.blog.controllers.boards;

import java.util.Map;

import org.blog.service.boards.BoardDataListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardListController {
	
	@Autowired
	private BoardDataListService service;
	
	@GetMapping("board/list")
	public String list(ListRequest listRequest, Model model) {
		
		Map<String, Object> result = service.get(listRequest);
		
		model.addAllAttributes(result);
		return "board/list";
	}
}
