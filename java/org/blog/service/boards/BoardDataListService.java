package org.blog.service.boards;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.blog.controllers.boards.ListRequest;
import org.blog.entity.BoardData;
import org.blog.entity.QBoardData;
import org.blog.entity.QMember;
import org.blog.repository.BoardDataRepository;
import org.blog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import org.blog.commons.Pagination;

@Service
public class BoardDataListService {
	
	@Autowired
	private BoardDataRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	public Map<String, Object> get(ListRequest listRequest) {
		
		/** 검색어 처리 S */
		String sopt = listRequest.getSopt();
		String skey = listRequest.getSkey();
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		if (sopt != null && !sopt.isBlank() && skey != null && !sopt.isBlank()) {
			//QMember member = QMember.member;
			QBoardData boardData = QBoardData.boardData;
			
			skey = skey.trim();
			BooleanExpression subjectConds = boardData.subject.contains(skey);
			BooleanExpression contentsConds = boardData.contents.contains(skey);
			BooleanExpression posterConds1 = boardData.poster.contains(skey);
			BooleanExpression posterConds2 = boardData.member.memNm.contains(skey);
			BooleanExpression memIdConds = boardData.member.memId.contains(skey);
			
			switch (sopt) {
				case "subject": 			// 제목
						booleanBuilder.and(subjectConds);
						break;
				case "contents": 			// 내용
						booleanBuilder.and(contentsConds);
						break;
				case "subject_contents": 	// 제목 + 내용 
						booleanBuilder.or(subjectConds).or(contentsConds);
						break;
				case "poster" : 
						booleanBuilder.or(posterConds1).or(posterConds2);
						break;
				case "memId" :
						booleanBuilder.and(memIdConds);
						break;
				default : 					// 통합검색
					booleanBuilder.or(subjectConds)
											.or(contentsConds)
											.or(posterConds1)
											.or(posterConds2)
											.or(memIdConds);
			}
		}
		/** 검색어 처리 E */
		
		Long _page = listRequest.getPage();
		int page = (int)((_page == null || _page <= 0) ? 1 : _page); 
		int limit = 10; // 1페이지당 레코드 수 20개
		
		Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Order.desc("regDt")));
		
		Page<BoardData> result = boardRepo.findAll(booleanBuilder, pageable);
		
		Pagination<BoardData> pagination = new Pagination<>(result);
		
		List<BoardData> list = result.getContent();
		long total = result.getTotalElements();
		int totalPages = result.getTotalPages();
		Map<String, Object> data = new HashMap<>();
		data.put("list", list);
		data.put("total", total);
		data.put("totalPages", totalPages);
		data.put("pagination",  pagination);
		
		return data;
	}
}
