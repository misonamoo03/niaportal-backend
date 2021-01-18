package com.misonamoo.niaportal.service;

import com.misonamoo.niaportal.domain.Board;
import com.misonamoo.niaportal.domain.BoardContent;

import java.util.List;

public interface BoardService {

    BoardContent getBoard(Long boardContentNo);

    Long insertBoard(BoardContent boardContent);

    Long updateBoard(BoardContent boardContent);

    void deleteBoard(Long boardContentNo);

    List<BoardContent> getBoardwithPagination(int page, int pageSize);

    Integer getBoardTotalCount();
}
