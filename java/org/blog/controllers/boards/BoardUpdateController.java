package org.blog.controllers.boards;

import javax.validation.Valid;

import org.blog.entity.BoardData;
import org.blog.service.boards.BoardDataInfoService;
import org.blog.service.boards.BoardDataSaveService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/update")
public class BoardUpdateController {
	
	@Autowired
	private BoardDataInfoService infoService;
	
	@Autowired
	private BoardDataSaveService saveService;
	
	@GetMapping("/{id}")
	public String form(@PathVariable Long id, Model model) {
		
		try {
			BoardData boardData = infoService.get(id);
			
			ModelMapper mapper = new ModelMapper();
			BoardRequest boardRequest = mapper.map(boardData, BoardRequest.class);
			model.addAttribute("boardRequest", boardRequest);
			
		} catch (RuntimeException e) {
			model.addAttribute("scripts", "alert('" + e.getMessage() + "');history.back();");
			return "common/sripts";
		}
		return "board/update";
	}
	
	@PostMapping
	public String process(@Valid BoardRequest boardRequest, Errors errors) {
		if (errors.hasErrors()) {
			return "board/update";
		}
		
		BoardData data = saveService.process(boardRequest);
		
		return "redirect:/board/view/" + data.getId();
	}
}
