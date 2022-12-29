package org.blog.service.boards;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.blog.controllers.boards.BoardRequest;
import org.blog.entity.BoardData;
import org.blog.entity.Member;
import org.blog.repository.BoardDataRepository;
import org.blog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardDataSaveService {
	
	@Autowired
	private BoardDataRepository repository;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberRepository memberRepository;
	
	public BoardData process(BoardRequest boardRequest) {
		Long id = boardRequest.getId();
		
		BoardData boardData = null;
		if(id != null) {
			boardData = repository.findById(id).orElse(null);
		}
		
		if(boardData == null) {	// 등록된 게시글이 아닌 경우 -> 추가
			boardData = new BoardData();
			
			Member member = (Member)session.getAttribute("member");
			if(member != null) {
				member = memberRepository.findByMemId(member.getMemId());
				
				boardData.setMember(member);
			}
			
		}
		
		boardData.setPoster(boardRequest.getPoster());
		boardData.setSubject(boardRequest.getSubject());
		boardData.setContents(boardRequest.getContents());
		
		boardData = repository.save(boardData);
		
		return boardData;
	}
}
