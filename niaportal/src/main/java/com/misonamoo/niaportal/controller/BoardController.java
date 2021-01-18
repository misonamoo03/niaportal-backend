package com.misonamoo.niaportal.controller;

import com.misonamoo.niaportal.common.util.ApiResponseMessage;
import com.misonamoo.niaportal.domain.BoardContent;
import com.misonamoo.niaportal.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

/**
 * 게시판 컨트롤러
 * @author Yohan
 */
@RestController
@RequestMapping("/board")
public class BoardController extends ApiResponseMessage {

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    BoardService boardService;

    // 권한에 따른 기능 제한(글쓰기 기능 제한 등등)
    
    /**
     * 목록 리턴.
     * @param page, pageSize
     * @return
     */
    @GetMapping("/list")
    public Map<String, Object> getBoardwithPagination(BoardContent boardContent,
                                                      @RequestParam(value =  "page", defaultValue = "1") int page,
                                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        log.info("getBoardwithPagination ㅡ 호출");
        System.out.println("page : " + boardContent.getPage() + " /pageSize : " + boardContent.getPageSize());
        Map<String, Object> map = new LinkedHashMap<>();

        //offsetNum 셋팅
        boardContent.setOffsetNum(page-1 * pageSize);

        if (isNull(boardContent.getBoardNo()) && isNull(boardContent.getSportsBoardCode())) {
            map.put("code",100);
            map.put("message","필수 변수값이 없습니다.");
            return map;
        }

        if (isNull(boardContent.getBoardNo())) {
            map.put("code",101);
            map.put("message","해당하는 게시판 번호가 없습니다.");
            return map;
        }

        // response에 totalCount 처리

        List list = boardService.getBoardwithPagination(page, pageSize);

        map.put("code",200);
        map.put("message","요청 성공");
        map.put("result", list);
        return map;
    }
/*
    // 총 목록 개수
    public ResponseEntity<Integer> getBoardTotalCount() throws Exception {
        log.info("selectBoardTotalCount ㅡ 호출");
        return new ResponseEntity<Integer>(boardService.getBoardTotalCount(), HttpStatus.OK);
    }
*/

    /**
     * 상세 정보 리턴.
     * @param boardContentNo
     * @return
     */
    @GetMapping("/detail/{boardContentNo}")
    public Map<String, Object> getBoard(@PathVariable Long boardContentNo) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", boardService.getBoard(boardContentNo));
        return resultMap;
    }

    /**
     * 등록 처리.
     * @param boardContent
     * @return
     */
    @PostMapping("/insert")
    public Map<String, Object> insertBoard(BoardContent boardContent) {
        Map<String, Object> map = new HashMap<>();

        Long boardContentNo = boardService.insertBoard(boardContent);

        map.put("code",200);
        map.put("message","요청 성공");
        map.put("boardContentNo", boardContentNo);
        return map;
    }

    /**
     * 수정 처리.
     * @param boardContent
     * @return
     */
    @PostMapping("/update")
    public Long updateBoard(BoardContent boardContent) {
        boardService.updateBoard(boardContent);
        return boardContent.getBoardContentNo();
    }

    /**
     * 삭제 처리.
     * @param boardContentNo
     * @return
     */
    // code, meesage 추가
    @PostMapping("/delete/{boardContentNo}")
    public boolean deleteBoard(@PathVariable Long boardContentNo) {
        BoardContent boardContent = boardService.getBoard(boardContentNo);
        if (boardContent == null) {
            return false;
        }
        boardService.deleteBoard(boardContentNo);
        return true;
    }

    public BoardContent getSportsBoardCode() {

    }
}
