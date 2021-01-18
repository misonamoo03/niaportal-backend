package com.misonamoo.niaportal.domain;

import lombok.Data;

import java.util.Date;

/**
 * 게시판 글관리
 * @author Yohan
 */
@Data
public class BoardContent {

    private Long boardContentNo;
    private Long userNo;
    private Long boardNo;
    private Long orgBoardContentNo;
    private String title;
    private String content;
    private String secYn;
    private Long viewCnt;
    private Date regDate;
    private Date updDate;
    private String replyYn;

    // 조인 컬럼
    private String sportsBoardCode;
    private int offsetNum;

}
