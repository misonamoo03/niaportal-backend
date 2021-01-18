package com.misonamoo.niaportal.domain;

import lombok.Data;

import java.util.Date;

/**
 * 게시판 관리
 * @author Yohan
 */
@Data
public class Board {

    private Long boardNo;
    private String name;
    private String boardTypeCode;
    private String sportsBoardCode;
    private Date regDate;
    private Long regUserNo;
    private Date updDate;
    private Long updUserNo;

}
