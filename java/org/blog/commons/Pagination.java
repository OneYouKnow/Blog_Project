package org.blog.commons;

import org.springframework.data.domain.Page;

import lombok.*;

@Getter @Setter @ToString
public class Pagination<T> {
	
	private int page; 		// 현재 페이지 번호
	private int noOfRange; 	// 1페이지당 페이지 번호 갯수
	private int totalPages;	// total 페이지 수
	private int startPage;
	private int endPage;
	private int prevPage;
	private int nextPage;
	
	public Pagination(Page<T> page) {
		this(page, 10);	// 기본값 10개
	}
	
	public Pagination(Page<T> page, int noOfRange){
		this.page = page.getPageable().getPageNumber() + 1;
		this.noOfRange = noOfRange;
		this.totalPages = page.getTotalPages();
		
		int no = 0;
		
		try {
			no = (int)Math.floor((this.page - 1) / noOfRange);
		} catch (RuntimeException e) {
			
		}
		
		int startPage = no * noOfRange + 1;
		int endPage = startPage + noOfRange - 1;
		if (endPage > totalPages) endPage = (int)totalPages;
		
		int lastNo = 0;
		try {
			lastNo = (int)Math.floor((endPage - 1) / noOfRange);	// 마지막 구간
		} catch (RuntimeException e) {}
		
		int prevPage = 0, nextPage = 0;
		
		if (no > 1) {	// 구간이 처음이 아닌 경우만 이전 구간 시작 페이지 번호
			prevPage = (no - 1) * noOfRange + 1;
		}
		
		if(no < lastNo) {	// 마지막 구간이 아닌 경우만 다음 페이지 구간으로 이동
			nextPage = prevPage + noOfRange;
		}
		
		this.startPage = startPage;
		this.endPage = endPage;
		this.prevPage = prevPage;
		this.nextPage = nextPage;
		
		
		// System.out.printf("start=%d, end=%d", startPage, endPage);
	}
}
