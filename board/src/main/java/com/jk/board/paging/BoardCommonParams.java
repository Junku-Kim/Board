package com.jk.board.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/*
 * MyBatis를 이용해 게시글 페이징과 검색을 하기 위해 필요한 필드를 가지고 있는 클래스입니다.
 */
public class BoardCommonParams {

	private int currentPage; // 현재 페이지
	private int recordPerPage; // 페이지 당 레코드 수
	private int pageSize; // 한 번에 보여줄 페이지 버튼 수
	private String keywordForSearch; // 검색 키워드
	private String searchType; // 검색 유형
	private Pagination pagination;
}
