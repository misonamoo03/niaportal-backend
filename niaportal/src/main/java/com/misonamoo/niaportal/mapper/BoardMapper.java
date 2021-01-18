package com.misonamoo.niaportal.mapper;

import com.misonamoo.niaportal.domain.Board;
import com.misonamoo.niaportal.domain.BoardContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 게시판 Mapper
 * @author Yohan
 */
@Repository
@Mapper
public interface BoardMapper {

    BoardContent getBoard(Long boardContentNo);

    Long insertBoard(BoardContent boardContentaram);

    Long updateBoard(BoardContent boardContent);

    void deleteBoard(Long boardContentNo);

    List<BoardContent> getBoardwithPagination(int page, int pageSize);

    Integer getBoardTotalCount();
}
