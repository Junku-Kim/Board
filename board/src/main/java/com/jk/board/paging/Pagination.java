package com.jk.board.paging;

import lombok.Getter;

@Getter
public class Pagination {

	private int totalRecordCount;
	private int totalPageCount;
	private int startPage;
	private int endPage;
	private int offsetStart;
	private boolean existPrevPage;
	private boolean existNextPage;
	
	public Pagination(int totalRecordCount, BoardCommonParams params) {
		if (totalRecordCount > 0) {
			this.totalRecordCount = totalRecordCount;
			this.calculation(params);
		}
	}

	private void calculation(BoardCommonParams params) {
		totalPageCount = ((totalRecordCount - 1) / params.getRecordPerPage()) + 1;
		
		if (params.getCurrentPage() > totalPageCount) {
			params.setCurrentPage(totalPageCount);
		}
		
		startPage = ((params.getCurrentPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;
		
		endPage = startPage + params.getPageSize() - 1;
		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		
		offsetStart = (params.getCurrentPage() - 1) * params.getRecordPerPage();
		
		existPrevPage = startPage != 1;
		
		existNextPage = (endPage * params.getRecordPerPage()) < totalRecordCount;
	}
}
