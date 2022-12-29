package org.blog.service.boards;

import org.blog.entity.BoardData;
import org.blog.repository.BoardDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardDataDeleteService {
	
	@Autowired
	private BoardDataRepository repository;
	
	public void process(Long id) {
		if (id == null) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		BoardData boardData = repository.findById(id).orElse(null);
		if(boardData == null) {
			throw new RuntimeException("등록된 게시글이 아닙니다.");
		}
		
		repository.delete(boardData);
	}
}
