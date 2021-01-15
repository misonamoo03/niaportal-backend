package com.misonamoo.niaportal.service;

import com.misonamoo.niaportal.domain.Board;
import com.misonamoo.niaportal.domain.BoardContent;

import java.util.List;

public interface BoardService {

    Board getBoard(Long getBoardSeq);

    Long insertBoard(BoardContent param);

    Long updateBoard(BoardContent param);

    void deleteBoard(Long getBoardSeq);

    List<Board> getBoardwithPagination(int limit, int offset);

    Integer getBoardTotalCount();
}
