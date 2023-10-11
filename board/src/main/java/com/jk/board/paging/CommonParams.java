package com.jk.board.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonParams {

	private int currentPage;
	private int recordPerPage;
	private int pageSize;
	private String keywordForSearch;
	private String searchType;
	private Pagination pagination;
}
