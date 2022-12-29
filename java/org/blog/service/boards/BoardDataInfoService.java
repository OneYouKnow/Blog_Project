package org.blog.service.boards;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.blog.entity.BoardData;
import org.blog.entity.Member;
import org.blog.repository.BoardDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardDataInfoService {
	
	@Autowired
	private BoardDataRepository repository;
	
	@PersistenceContext
	private EntityManager em;
	
	public BoardData get(Long id) {
		
		// 아이디 없을 때
		if(id == null) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		
		BoardData boardData = repository.findById(id).orElse(null);
		if(boardData != null) {
			
			Member member = boardData.getMember();
			/**
			if(member != null) {
				member.getMemId();
				em.detach(member);
			}
			*/
			em.detach(boardData);
			boardData.setMember(member);
		} else {
			throw new RuntimeException("등록되지 않은 게시글입니다.");
		}
		
		
		return boardData;
	}
}
