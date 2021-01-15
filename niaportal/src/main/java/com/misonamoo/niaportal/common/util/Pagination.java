package com.misonamoo.niaportal.common.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Yohan
 */

@Getter
@Setter
public class Pagination {

    private int listCount = 10; // 한 페이지당 목록 개수
    private int pageCount = 10; //
    private int page; // 현재 페이지
    private int range;
    private int totalCount;
    private int totalPage; // 전체 페이지 개수
    private int startPage; // 시작 페이지
    private int startList;
    private int endPage; // 끝 페이지
    private boolean prev, next; // 이전, 다음

    public void pageInfo(int page, int range, int totalCount) {
        this.page  = page;
        this.range = range;
        this.totalCount = totalCount;

        // 전체 페이지 수
        this.totalPage = (int)Math.ceil(totalCount / listCount);

        // 시작 페이지
        this.startPage = (range - 1) * pageCount + 1;

        // 끝 페이지
        this.endPage = range * pageCount;

        // 게시판 시작번호
        this.startList = (page - 1) * listCount;

        // 이전 버튼 상태
        this.prev = range == 1 ? false : true;

        // 다음 버튼 상태
        this.next = endPage > totalPage ? false : true;
        if (this.endPage > this.totalPage) {
            this.endPage = this.totalPage;
            this.next = false;
        }
    }

}
