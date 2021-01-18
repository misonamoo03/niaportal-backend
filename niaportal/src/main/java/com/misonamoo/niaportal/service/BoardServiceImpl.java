package com.misonamoo.niaportal.service;

import com.misonamoo.niaportal.domain.Board;
import com.misonamoo.niaportal.domain.BoardContent;
import com.misonamoo.niaportal.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 게시판 서비스
 * @author Yohan
 */
@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardMapper boardMapper;

    /**
     * 목록 리턴.
     * @return
     */
    public List<BoardContent> getBoardwithPagination(int page, int pageSize) {
        return boardMapper.getBoardwithPagination(page, pageSize);
    }

    /**
     * 상세 정보 리턴.
     * @param boardContentNo
     * @return
     */
    public BoardContent getBoard(Long boardContentNo) {
        return boardMapper.getBoard(boardContentNo);
    }

    /**
     * 등록 처리.
     * @param boardContent
     * @return
     */
    public Long insertBoard(BoardContent boardContent) {
        boardMapper.insertBoard(boardContent);
        return boardContent.getBoardContentNo();
    }

    /**
     * 수정 처리.
     * @param boardContent
     * @return
     */
    public Long updateBoard(BoardContent boardContent) {
        // 조회하여 리턴된 정보
        BoardContent content = boardMapper.getBoard(boardContent.getBoardContentNo());
        boardMapper.updateBoard(content);
        return boardContent.getBoardContentNo();
    }

    /**
     * 삭제 처리.
     * @param boardContentNo
     */
    public void deleteBoard(Long boardContentNo) {
        boardMapper.deleteBoard(boardContentNo);
    }


    @Override
    public Integer getBoardTotalCount() {
        return boardMapper.getBoardTotalCount();
    }
}
