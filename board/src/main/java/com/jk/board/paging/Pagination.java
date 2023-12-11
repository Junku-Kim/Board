package com.jk.board.paging;

import lombok.Getter;

@Getter
/*
 * 페이지네이션을 위한 값들을 가지고 있는 클래스입니다.
 */
public class Pagination {

	private int totalRecordCount; // 전체 레코드(데이터) 수
	private int totalPageCount; // 전체 페이지 수
	private int startPage; // 첫 페이지 버튼 번호
	private int endPage; // 마지막 페이지 버튼 번호
	private int offsetStart; // 데이터 offset 시작 위치
	private boolean existPrevPage; // 이전 페이지 존재 여부
	private boolean existNextPage; // 다음 페이지 존재 여부
	
	public Pagination(int totalRecordCount, BoardCommonParams params) {
		// 레코드가 1개라도 있을 경우
		if (totalRecordCount > 0) {
			this.totalRecordCount = totalRecordCount;
			this.calculation(params);
		}
	}

	// 필요한 값을 계산하는 메서드
	private void calculation(BoardCommonParams params) {
		totalPageCount = ((totalRecordCount - 1) / params.getRecordPerPage()) + 1;
		
		// 전체 페이지 수보다 현재 페이지가 높게 설정되어 있을 경우
		if (params.getCurrentPage() > totalPageCount) {
			params.setCurrentPage(totalPageCount);
		}
		
		startPage = ((params.getCurrentPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;
		
		endPage = startPage + params.getPageSize() - 1;
		// 마지막 페이지가 총 페이지 수보다 클 경우
		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		
		offsetStart = (params.getCurrentPage() - 1) * params.getRecordPerPage();
		
		existPrevPage = startPage != 1;
		
		existNextPage = (endPage * params.getRecordPerPage()) < totalRecordCount;
	}
}
