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

    Board getBoard(Long boardSeq);

    Long insertBoard(BoardContent param);

    Long updateBoard(BoardContent param);

    void deleteBoard(Long boardNo);

    List<Board> getBoardwithPagination(@Param("limit") int limit, @Param("offset") int offset);

    Integer getBoardTotalCount();
}
